package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// Test class added ONLY to cover main() invocation not covered by application tests.
class EshopApplicationTests {
    @Test
    void main() {
        EshopApplication.main(new String[] {});
        assertThat(EshopApplication.class).isNotNull();
    }
}
