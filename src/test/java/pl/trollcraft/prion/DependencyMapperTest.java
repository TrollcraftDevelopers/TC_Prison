package pl.trollcraft.prion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pl.trollcraft.prison.service.pluginLoader.DependencyMapper;

import java.util.Optional;

public class DependencyMapperTest {

    private static class TestClassA {}
    private static class TestClassB {}

    @Test
    void shouldReturnValidObjectsForClasses() {
        TestClassA testClassA = new TestClassA();
        TestClassB testClassB = new TestClassB();

        DependencyMapper dependencyMapper = new DependencyMapper();
        dependencyMapper.registerDependency(testClassA);
        dependencyMapper.registerDependency(testClassB);

        Optional<TestClassA> dependencyTestClassA = dependencyMapper.getDependency(TestClassA.class);
        Optional<TestClassB> dependencyTestClassB = dependencyMapper.getDependency(TestClassB.class);

        assertTrue(dependencyTestClassA.isPresent());
        assertTrue(dependencyTestClassB.isPresent());
        assertEquals(dependencyTestClassA.get(), testClassA);
        assertEquals(dependencyTestClassB.get(), testClassB);
    }

}
