package com.controller;

import com.controller.service.*;
import com.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@org.springframework.stereotype.Controller
public class Controller {

    private Random r = new Random();

    @Autowired
    public CountryService countryService;

    @Autowired
    public CityService cityService;

    @Autowired
    public HotelService hotelService;

    @Autowired
    public TourService tourService;

    @Autowired
    public TourAddServiceService tourAddServiceService;

    @Autowired
    public InsuranceService insuranceService;

    @Autowired
    public InsuranceOptionService insuranceOptionService;

    @Autowired
    public PersonInfoService personInfoService;

    @Autowired
    public ClientService clientService;

    @Autowired
    public TourStaffService tourStaffService;

    @Autowired
    public OrderService orderService;

    @Autowired
    public ChoosenServiceService choosenServiceService;

    @Autowired
    public TouristsService touristsService;

    @Autowired
    public PersonInsuranceService personInsuranceService;

    @Autowired
    private HttpServletRequest request;

    @ModelAttribute("orderForm")
    public void getOrderForm(Model model){
        model.addAttribute("orderForm", new OrderForm());
    }

    @ModelAttribute("choosenServices")
    public ChoosenServiceForm getChoosenServiceForm(){
        return new ChoosenServiceForm();
    }

    @ModelAttribute("tourists")
    public void getTourists(Model model){
        model.addAttribute("tourists", new Tourists());
    }

    @ModelAttribute("touristForm")
    public void getTourist(Model model){
        model.addAttribute("touristForm", new TouristForm());
    }

    @ModelAttribute("client")
    public void getClient(Model model){
        model.addAttribute("clientForm", new ClientForm());
    }

    @ModelAttribute("allInsurance")
    public List<Insurance> getAllInsurance(){
        return insuranceService.findAll();
     }

    @ModelAttribute("insuranceForm")
    public void getInsuranceForm(Model model){
        model.addAttribute("insuranceForm", new InsuranceForm());
    }


    @GetMapping
    public String main(Model model){
        StringBuilder elapsedTime = new StringBuilder();
        long startTime = System.nanoTime();
        List<Country> countries = countryService.findAll();
        elapsedTime.append("Elapsed time: ").append(System.nanoTime() - startTime);
        System.out.println(elapsedTime);
        model.addAttribute("countries", countries);
        return "main";
}

    @PostMapping("/foundTours")
    public String findTours(@RequestParam Country country, @RequestParam("numOfAdults") Integer numOfAdults,
                            @RequestParam("numOfChildren") Integer numOfChildren, Model model){
        Long id_country = country.getId();
        List<City> cities = cityService.citiesOfCountry(id_country);
        List<Hotel> hotels = new ArrayList<>();
        for (City city : cities) {
             hotels.addAll(hotelService.hotelsForCities(city.getId()));
        }
        List<Tour> foundTours = new ArrayList<>();
        for (Hotel hotel : hotels){
            foundTours.addAll(tourService.toursForHotels(hotel.getId(), numOfAdults, numOfChildren));
        }
        model.addAttribute("foundTours", foundTours);
        return "foundTours";
    }

    @PostMapping("/tourInfo")
    public String tourInfo(@RequestParam Tour tour, Model model){
        Long id_tour = tour.getId();
        String hotelName = tour.getId_hotel().getName();
        String typeOfFood = tour.getId_type_of_food().getType_of_food();
        String typeOfRoom = tour.getId_type_of_room().getType_of_room();
        Integer numOfAdults = tour.getNum_of_adults();
        Integer numOfChildren = tour.getNum_of_children();
        BigDecimal oneNightPrice = tour.getOne_night_price();
        City tourCity = tour.getId_hotel().getId_city();
        List<TourAddService> addServicesForTour = tourAddServiceService.addServiceForTour(tour);
        StringBuilder addServices = new StringBuilder();
        if (addServicesForTour.size() != 0) {
            for (int i = 0; i < addServicesForTour.size(); i++) {
                if (i != addServicesForTour.size() - 1) {
                    addServices.append(addServicesForTour.get(i).getId_add_service().getName()).append(", ");
                } else {
                    addServices.append(addServicesForTour.get(i).getId_add_service().getName()).append(".");
                }
            }
        } else {
            addServices.append("Нет услуг.");
        }
        model.addAttribute("id_tour", id_tour);
        model.addAttribute("hotelName", hotelName);
        model.addAttribute("typeOfFood", typeOfFood);
        model.addAttribute("typeOfRoom", typeOfRoom);
        model.addAttribute("numOfAdults", numOfAdults);
        model.addAttribute("numOfChildren", numOfChildren);
        model.addAttribute("oneNightPrice", oneNightPrice);
        model.addAttribute("addServicesList", addServicesForTour);
        model.addAttribute("addServices", addServices);
        model.addAttribute("tourCity", tourCity);
        model.addAttribute("tour", tour);
        return "tourInfo";
    }

    @PostMapping("/stepOneAddService")
    public String stepOneAddService(@RequestParam Long goToStepOne,
                                    Model model){
        Tour tour = tourService.findById(goToStepOne);
        List<TourAddService> addServicesForTour =
                tourAddServiceService.addServiceForTour(tour);
        request.getSession().setAttribute("asl", addServicesForTour);
        model.addAttribute("asl", addServicesForTour);
        model.addAttribute("tour", tour);
        return "stepOneAddService";
    }

    @PostMapping(value = "/stepTwoTourists")
    public String goToStepTwo(@ModelAttribute ChoosenServiceForm choosenServices,
                              @RequestParam Tour goToStepTwo, Model model){
        request.getSession().setAttribute("csf", choosenServices);
        Tour tour = goToStepTwo;
        int numOfAll = goToStepTwo.getNum_of_adults() + goToStepTwo.getNum_of_children();
        TouristForm[] t = new TouristForm[numOfAll];
        model.addAttribute("touristsForOrder", t);
        model.addAttribute("numOfAll", numOfAll);
        model.addAttribute("tour", tour);
        return "stepTwoTourists";
    }

    @PostMapping("/stepThreeInsurance")
    public String goToStepThree(@RequestParam Tour goToStepThree,
                                @ModelAttribute("allInsurance") List<Insurance> allInsurance, Model model,
                                @ModelAttribute("tourists") Tourists tourists,
                                @ModelAttribute("client") ClientForm clientForm){
        request.getSession().setAttribute("tourists", tourists);
        request.getSession().setAttribute("clientForm", clientForm);
        Tour tour = goToStepThree;
        int numOfAll = goToStepThree.getNum_of_adults() + goToStepThree.getNum_of_children();
        TouristForm[] t = new TouristForm[numOfAll];
        Map<Insurance, List<String>> insuranceInfo = new HashMap<>();
        for (Insurance insurance : allInsurance) {
            List<InsuranceOption> insuranceOptionsList = insuranceOptionService.optionsForInsurance(insurance);
            List<String> insuranceOptionsStringList = new ArrayList<>();
            for (InsuranceOption insuranceOption : insuranceOptionsList) {
                String s = insuranceOption.getId_insurance_type().getName() +
                        ", страховая выплата: " + insuranceOption.getSum_insured() + "$";
                insuranceOptionsStringList.add(s);
            }
            insuranceInfo.put(insurance, insuranceOptionsStringList);
        }
        model.addAttribute("tour", tour);
        model.addAttribute("touristsForOrder", t);
        model.addAttribute("insInfo", insuranceInfo);
        return "stepThreeInsurance";
    }

    @PostMapping(value = "/stepFourOrderForm")
    public String goToStepFour(@RequestParam Tour goToStepFour, Model model,
                               @ModelAttribute("insuranceForm") InsuranceForm insuranceForm){
        request.getSession().setAttribute("insuranceForm", insuranceForm);
        Tour tour = goToStepFour;
        model.addAttribute("tour", tour);
        return "stepFourOrderForm";
    }

    @PostMapping(value = "/stepFinal")
    public String goToFinalStep(@RequestParam Tour goToFinalStep, Model model,
                               @ModelAttribute("orderForm") OrderForm orderForm){
        request.getSession().setAttribute("orderForm", orderForm);
        BigDecimal sum = new BigDecimal(0);
        int numOfNights = orderForm.getNumberOfNights();
        List<TourAddService> asl = (List<TourAddService>) request.getSession().getAttribute("asl");
        ChoosenServiceForm csf = (ChoosenServiceForm) request.getSession().getAttribute("csf");
        InsuranceForm insuranceForm = (InsuranceForm) request.getSession().getAttribute("insuranceForm");
        ArrayList<Integer> csf_arr = csf.getAmount();
        List<String> addServiceInfo = new ArrayList<>();
        for (int i=0; i < asl.size(); i++){
            if (csf_arr.get(i) != null) {
                String s = asl.get(i).getId_add_service().getName() + " " + csf_arr.get(i) + "ед.";
                addServiceInfo.add(s);
            }
        }
        Tour tour = goToFinalStep;
        int numOfAll = tour.getNum_of_adults() + tour.getNum_of_children();
        int[] arr = new int[numOfAll];
        BigDecimal sumForTour = tour.getOne_night_price().multiply(BigDecimal.valueOf(numOfNights));
        BigDecimal sumForAddService = new BigDecimal(0);
        BigDecimal sumForInsurance = new BigDecimal(0);
            for (int i = 0; i < asl.size(); i++){
                if (csf_arr.get(i) != null) {
                    BigDecimal cost = asl
                            .get(i).getId_add_service().getSurcharge()
                            .multiply(BigDecimal.valueOf(csf_arr.get(i)));
                    sumForAddService = sumForAddService.add(cost);
                }
            }
        List<Insurance> li = Utilities.insuranceFormToList(insuranceForm);
        for (int i = 0; i < numOfAll; i++){
            BigDecimal cost = li.get(i).getOne_day_price().multiply(BigDecimal.valueOf(numOfNights));
            sumForInsurance = sumForInsurance.add(cost);
        }
        sum = sum.add(sumForTour.add(sumForAddService.add(sumForInsurance)));
        model.addAttribute("fullPrice", sum);
        model.addAttribute("arr", arr);
        model.addAttribute("addServiceInfo", addServiceInfo);
        model.addAttribute("tour", tour);
        return "stepFinal";
    }

    @PostMapping("/readyPage")
    public String readyStep(@RequestParam Tour goToReadyPage){
        Tour tour = goToReadyPage;
        Tourists tourists = (Tourists) request.getSession().getAttribute("tourists");
        OrderForm orderForm = (OrderForm) request.getSession().getAttribute("orderForm");
        ClientForm clientForm = (ClientForm) request.getSession().getAttribute("clientForm");
        ChoosenServiceForm csf = (ChoosenServiceForm) request.getSession().getAttribute("csf");
        InsuranceForm insuranceForm = (InsuranceForm) request.getSession().getAttribute("insuranceForm");
        List<TourAddService> asl = (List<TourAddService>) request.getSession().getAttribute("asl");
        List<Insurance> insuranceList = Utilities.insuranceFormToList(insuranceForm);
        int numOfAll = goToReadyPage.getNum_of_adults() + goToReadyPage.getNum_of_children();
        List<TouristForm> touristFormList = Utilities.touristFormToList(tourists);
        List<PersonInfo> pil = new ArrayList<>();
        PersonInfo clientPI;
        for (int i = 0; i < numOfAll; i++){
            PersonInfo pi1 = personInfoService.findExisting(touristFormList.get(i).getSurname(),
                    touristFormList.get(i).getName(), touristFormList.get(i).getMiddle_name(),
                    LocalDate.parse(touristFormList.get(i).getDob()), touristFormList.get(i).getPhone(),
                    touristFormList.get(i).getEmail(), touristFormList.get(i).getIntl_passport_num(),
                    touristFormList.get(i).getIntl_passport_series());
            if (pi1 == null) {
                PersonInfo personInfo = new PersonInfo();
                personInfo.setSurname(touristFormList.get(i).getSurname());
                personInfo.setName(touristFormList.get(i).getName());
                personInfo.setMiddle_name(touristFormList.get(i).getMiddle_name());
                personInfo.setDob(LocalDate.parse(touristFormList.get(i).getDob()));
                personInfo.setPhone(touristFormList.get(i).getPhone());
                personInfo.setEmail(touristFormList.get(i).getEmail());
                personInfo.setIntl_passport_num(touristFormList.get(i).getIntl_passport_num());
                personInfo.setIntl_passport_series(touristFormList.get(i).getIntl_passport_series());
                PersonInfo p = personInfoService.createPersonInfo(personInfo);
                pil.add(p);
            } else pil.add(pi1);
        }
        Client client = new Client();
        Client checkClient2 = clientService.findExistingClient(clientForm.getPassport_num(),
                clientForm.getPassport_series());

        if (checkClient2 == null) {
            PersonInfo checkClientAmongTourists = Utilities.checkClient(clientForm, pil);
            PersonInfo checkClientInDB = personInfoService.findExisting2(clientForm.getSurname(),
                    clientForm.getName(), clientForm.getMiddle_name(), LocalDate.parse(clientForm.getDob()),
                    clientForm.getPhone(), clientForm.getEmail());
            if (checkClientAmongTourists == null & checkClientInDB == null) {
                PersonInfo personInfo = new PersonInfo();
                personInfo.setSurname(clientForm.getSurname());
                personInfo.setName(clientForm.getName());
                personInfo.setMiddle_name(clientForm.getMiddle_name());
                personInfo.setDob(LocalDate.parse(clientForm.getDob()));
                personInfo.setPhone(clientForm.getPhone());
                personInfo.setEmail(clientForm.getEmail());
                personInfo.setIntl_passport_num(null);
                personInfo.setIntl_passport_series(null);
                clientPI = personInfoService.createPersonInfo(personInfo);
                Client c = new Client();
                c.setId_person_info(clientPI);
                c.setPassport_num(clientForm.getPassport_num());
                c.setPassport_series(clientForm.getPassport_series());
                client = clientService.createClient(c);
            } else if (checkClientInDB != null & checkClientAmongTourists == null) {
                Client c = new Client();
                c.setId_person_info(checkClientInDB);
                c.setPassport_num(clientForm.getPassport_num());
                c.setPassport_series(clientForm.getPassport_series());
                client = clientService.createClient(c);
            } else if (checkClientInDB == null){
                Client c = new Client();
                c.setId_person_info(checkClientAmongTourists);
                c.setPassport_num(clientForm.getPassport_num());
                c.setPassport_series(clientForm.getPassport_series());
                client = clientService.createClient(c);
            } else {
                Client c = new Client();
                c.setId_person_info(checkClientInDB);
                c.setPassport_num(clientForm.getPassport_num());
                c.setPassport_series(clientForm.getPassport_series());
                client = clientService.createClient(c);
            }
        } else {
            client = checkClient2;
        }
        List<TourStaff> managers = tourStaffService.findAllManagers();
        Order order = new Order();
        order.setId_client(client);
        order.setId_tour(tour);
        order.setDeparture_date(LocalDate.parse(orderForm.getDepartureDate()));
        order.setNumber_of_nights(orderForm.getNumberOfNights());
        order.setId_tour_staff(managers.get(r.nextInt(managers.size())));
        order.setOrder_date_time(LocalDateTime.now());
        Order o = orderService.createOrder(order);

        for (int i = 0; i < asl.size(); i++){
            if (csf.getAmount().get(i) != null){
                ChoosenService choosenService = new ChoosenService();
                choosenService.setId_add_service(asl.get(i).getId_add_service());
                choosenService.setId_order(o);
                choosenService.setAmount(csf.getAmount().get(i));
                choosenServiceService.createChoosenService(choosenService);
            }
        }
        List<com.entity.Tourists> t = new ArrayList<>();
        for (int i = 0; i < pil.size(); i++){
            com.entity.Tourists tourists1 = new com.entity.Tourists();
            tourists1.setId_person_info(pil.get(i));
            tourists1.setId_order(o);
            com.entity.Tourists returnedTourist = touristsService.createTourists(tourists1);
            t.add(returnedTourist);
        }

        for (int i = 0; i < t.size(); i++){
            PersonInsurance personInsurance = new PersonInsurance();
            personInsurance.setTourists(t.get(i));
            personInsurance.setId_insurance(insuranceList.get(i));
            String policyNumber = Utilities.generatePolicyNumber(r);
            if (personInsuranceService.findPolicyNum(policyNumber) != null){
              while (policyNumber.equals(personInsuranceService.findPolicyNum(policyNumber).getPolicy_num())){
                policyNumber = Utilities.generatePolicyNumber(r);
                }
            }
            personInsurance.setPolicy_num(policyNumber);
            personInsuranceService.createPersonInsurance(personInsurance);
        }
        return "readyPage";
    }
}
