package oncall.config;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.function.Supplier;
import java.util.stream.Stream;
import oncall.controller.OncallController;
import oncall.service.OncallService;
import oncall.view.InputView;
import oncall.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class AppConfigTest {
    private static AppConfig config;

    @BeforeEach
    void setUp() {
        config = AppConfig.getInstance();
    }

    static class ObjectSupplierTuple {
        final Supplier<Object> supplier;
        final Class<?> expectedType;

        ObjectSupplierTuple(final Supplier<Object> supplier, final Class<?> expectedType) {
            this.supplier = supplier;
            this.expectedType = expectedType;
        }
    }

    static Stream<ObjectSupplierTuple> singletonSuppliers() {
        return Stream.of(
                new ObjectSupplierTuple(() -> config.outputView(), OutputView.class),
                new ObjectSupplierTuple(() -> config.inputView(), InputView.class),
                new ObjectSupplierTuple(() -> config.oncallController(), OncallController.class),
                new ObjectSupplierTuple(() -> config.oncallService(), OncallService.class)
        );
    }

    @ParameterizedTest(name = "{0}은 싱글톤이며, {1}의 객체이다.")
    @MethodSource("singletonSuppliers")
    void assertSingletonAndType(final ObjectSupplierTuple tuple) {
        // Given
        final Supplier<Object> supplier = tuple.supplier;
        final Class<?> expectedType = tuple.expectedType;

        // When
        Object firstInstance = supplier.get();
        Object secondInstance = supplier.get();

        // Then
        assertThat(firstInstance).isNotNull();
        assertThat(firstInstance).isInstanceOf(expectedType);
        assertThat(firstInstance).isSameAs(secondInstance);
    }

}
