package com.shop.restfull.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.restfull.dto.CustomerBasicDto;
import com.shop.restfull.dto.CustomerDto;
import com.shop.restfull.model.Usuario;
import com.shop.restfull.repository.test.JsonUtil;
import com.shop.restfull.service.IUsuarioService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ManagerControllerTest {
	@Autowired
    private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
    private IUsuarioService usuarioService;
	
	@Test
	public void obtenerMenuPrincipalShouldReturn200() throws Exception {
		mockMvc.perform(get("/manager/obtenerMenuPrincipal")
			    .contentType("application/json"))
			    .andExpect(status().isOk());
	}
	
	@Test
	public void getCustomerBasicListShouldNotBeEmpty() throws Exception {
		
		CustomerBasicDto customer1 = new CustomerBasicDto(1, "Jose", "Perez", "jope", "email");
		CustomerBasicDto customer2 = new CustomerBasicDto(2, "Maria", "Loca", "malo", "email2");
		List<CustomerBasicDto> customersBasic = new ArrayList<CustomerBasicDto>();
		customersBasic.add(customer1);
		customersBasic.add(customer2);
		
		when(usuarioService.obtenerCustomerBasicList()).thenReturn(customersBasic);
		mockMvc.perform(get("/manager/getCustomerBasicList")
			    .contentType("application/json"))
				.andDo(print())
				.andExpect(jsonPath("$", hasSize(2)))
			    .andExpect(status().isOk());
	}
	
	@Test
	public void getCustomerBasicListShouldBeEmpty() throws Exception {
		
		mockMvc.perform(get("/manager/getCustomerBasicList")
			    .contentType("application/json"))
				.andDo(print())
			    .andExpect(status().isNoContent());
	}
	
	@Test
	public void getCustomerBasicShouldNotBeEmpty() {
		
		CustomerDto customer = new CustomerDto();
		customer.setId(2);
		customer.setNombre("Hugo");
		customer.setApellido("Onetto");
		customer.setUsuario("huguisho");

		when(usuarioService.obtenerCustomerBasic(customer.getId())).thenReturn(customer);
		try {
			mockMvc.perform(get("/manager/getCustomerBasic")
					.param("id", "2")
				    .contentType(MediaType.APPLICATION_JSON)
				    .content(JsonUtil.toJson(customer)))
					.andDo(print())
					.andExpect(jsonPath("$.nombre", is("Hugo")))
				    .andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getCustomerBasicShouldNotFound() {
		
		CustomerDto customer = new CustomerDto();
		customer.setId(2);
		customer.setNombre("Hugo");
		customer.setApellido("Onetto");
		customer.setUsuario("huguisho");

		when(usuarioService.obtenerCustomerBasic(customer.getId())).thenReturn(customer);
		try {
			mockMvc.perform(get("/manager/getCustomerBasic")
					.param("id", "3")
				    .contentType(MediaType.APPLICATION_JSON)
				    .content(JsonUtil.toJson(customer)))
					.andDo(print())
				    .andExpect(status().is4xxClientError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
