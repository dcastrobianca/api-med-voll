package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.doctor.DoctorService;
import med.voll.api.domain.doctor.Speciality;
import med.voll.api.domain.doctor.dto.DoctorDetailsData;
import med.voll.api.domain.doctor.dto.DoctorRegistrationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DoctorRegistrationData> doctorRegistrationJson;
    @Autowired
    private JacksonTester<DoctorDetailsData> doctorDetailsDataJson;

    @MockBean
    private DoctorService service;

    @Test
    @DisplayName("Invalid data sent to create doctor, should return error 400")
    @WithMockUser
    void shouldReturnHttpCode400WhenThereIsNoBodyInRequest() throws Exception {
        var response = mockMvc.perform(post("/doctors"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Create Doctor when receive valid JSON, should return code 201 ")
    @WithMockUser
    void shouldReturnHttpCode201() throws Exception {
        //given
        String name = "Fulano da Silva";
        String email = "fulano.silva@voll.med";
        String phone = "123456789";
        String crm = "111223";
        Speciality speciality = Speciality.CARDIOLOGY;
        AddressData addressData = new AddressData("Street", "district", "77600000", "City", "State", null, null);
        Address address = new Address(addressData);

        DoctorRegistrationData registrationData = new DoctorRegistrationData(name, email, phone, crm, speciality, addressData);
        DoctorDetailsData detailsData = new DoctorDetailsData(null, name, crm, email, phone, speciality, address);

        when(service.create(registrationData)).thenReturn(detailsData);

        //when
        var response = mockMvc.perform(
                        post("/doctors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(doctorRegistrationJson.write(registrationData).getJson()))
                .andReturn().getResponse();


        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(doctorDetailsDataJson.write(detailsData).getJson());
    }
}