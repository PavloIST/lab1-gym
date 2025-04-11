package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class Tests {
    private final Gym gym = new Gym();

    @BeforeEach
    public void clearVisitLog() {
        try (FileWriter writer = new FileWriter("visit_log.txt", false)) {
            writer.write("");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Test 1
    @Test
    public void addVisitorTest1(){
        gym.addVisitor(new Visitor("Pavlo",18,1));

        assertNotNull(gym.getVisitor(1));
    }

    // Test 2
    @Test
    public void addVisitorTest2(){
        gym.addVisitor(new Visitor("Pavlo",18,1));
        gym.addVisitor(new Visitor("Pavlo",18,1));
        gym.addVisitor(new Visitor("Pavlo",18,1));
        gym.addVisitor(new Visitor("Pavlo",18,1));
        gym.addVisitor(new Visitor("Pavlo",18,1));

        assertEquals(1, gym.visitorList.size());
    }

    // Test 3
    @Test
    public void addVisitorTest3(){
        gym.addVisitor(new Visitor("Pavlo",18,1));
        gym.addVisitor(new Visitor("Anna",19,2));
        gym.addVisitor(new Visitor("Misha",20,5));
        gym.addVisitor(new Visitor("Max",35,3));
        gym.addVisitor(new Visitor("Kirill",41,7));
        gym.addVisitor(new Visitor("Oleksii",16,8));

        assertEquals(6, gym.visitorList.size());
    }

    // Test 4
    @Test
    public void updateVisitorTest1() {
        gym.addVisitor(new Visitor("Oleksii",16,8));
        gym.updateVisitor(8, "Anton", 50);
        assertEquals("Anton", gym.getVisitor(8).getName());
    }

    // Test 5
    @Test
    public void updateVisitorTest2() {
        assertThrows(IllegalArgumentException.class, () -> gym.updateVisitor(100,"Lex",1000));
    }

    // Test 6
    @Test
    public void trainingWithTrainerTest() {
        Trainer t1 = new Trainer("Anna",30,1);
        Visitor v1 = new Visitor("Pavlo",18,1);
        gym.addVisitor(v1);
        gym.addTrainer(t1);
        gym.trainingWithTrainer(v1,t1);
        assertEquals(1, gym.getHistory().size());
    }
    // Test 7
    @Test
    public void goToGymTest() {
        Visitor v1 = new Visitor("Pavlo",18,1);
        gym.addVisitor(v1);
        v1.goToGym(gym,false);
        assertEquals(1, gym.getHistory().size());
    }

    // Test 8
    @Test
    public void exportVisitorsToJson_withMock_shouldCallWriteValue() throws IOException {
        Gym testGym = new Gym();
        testGym.addVisitor(new Visitor("Pavlo", 18, 1));
        testGym.addVisitor(new Visitor("Anna", 19, 2));

        testGym.exportVisitorsToJson("test_export.json");
        File tempFile = new File("test_export.json");
        tempFile.deleteOnExit();
        assertTrue(tempFile.exists());
    }
    // Test 9
    @Test
    public void importVisitorsFromJsonTest() throws IOException {
        String json = """
        [
           { "id": 1, "name": "Pavlo", "age": 25 },
           { "id": 2, "name": "Anna", "age": 30 },
           { "id": 3, "name": "Petro", "age": 28 }
        ]
        """;
        File tempFile = File.createTempFile("visitors", ".json");
        tempFile.deleteOnExit();

        FileWriter writer = new FileWriter(tempFile);
        writer.write(json);
        writer.close();

        Gym testGym = new Gym();
        testGym.importVisitorsFromJson(tempFile.getAbsolutePath());

        assertEquals("Pavlo", testGym.getVisitor(1).getName());
        assertEquals("Anna", testGym.getVisitor(2).getName());
        assertEquals("Petro", testGym.getVisitor(3).getName());
    }

}
