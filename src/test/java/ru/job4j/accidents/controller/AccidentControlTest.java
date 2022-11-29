package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControlTest {

    @MockBean
    private AccidentService accidentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAndNewAccident() throws Exception {
        this.mockMvc.perform(post("/createAccident")
                .param("id", "1")
                .param("name", "Авария")
                .param("text", "Никто не пострадал")
                .param("address", "Косой переулок")
                .param("accidentType.id", "1")
                .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).save(argument.capture());
        assertThat(argument.getValue().getId()).isEqualTo(1);
        assertThat(argument.getValue().getName()).isEqualTo("Авария");
        assertThat(argument.getValue().getText()).isEqualTo("Никто не пострадал");
        assertThat(argument.getValue().getAddress()).isEqualTo("Косой переулок");
        assertThat(argument.getValue().getAccidentType().getId()).isEqualTo(1);
        assertThat(argument.getValue().getRules().stream().findAny().get().getId()).isEqualTo(1);
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAndUpdatedAccident() throws Exception {
        this.mockMvc.perform(post("/updateAccident")
                        .param("id", "1")
                        .param("name", "Авария")
                        .param("text", "Никто не пострадал")
                        .param("address", "Косой переулок")
                        .param("accidentType.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).save(argument.capture());
        assertThat(argument.getValue().getId()).isEqualTo(1);
        assertThat(argument.getValue().getName()).isEqualTo("Авария");
        assertThat(argument.getValue().getText()).isEqualTo("Никто не пострадал");
        assertThat(argument.getValue().getAddress()).isEqualTo("Косой переулок");
        assertThat(argument.getValue().getAccidentType().getId()).isEqualTo(1);
        assertThat(argument.getValue().getRules().stream().findAny().get().getId()).isEqualTo(1);
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAndViewFormCreateAccident() throws Exception {
        this.mockMvc.perform(get("/formCreateAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("formCreateAccident"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAndViewFormUpdateAccident() throws Exception {
        when(accidentService.findById(1)).thenReturn(Optional.of(new Accident()));
        this.mockMvc.perform(get("/formUpdateAccident").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("formUpdateAccident"));
    }
}