package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MainControllerTests {

    @Mock
    private Model model;

    @InjectMocks
    MainController mainController;

    @Test
    void testMainPage() {
        String result = mainController.mainPage(model);
        assertEquals("mainPage",result);
    }
}
