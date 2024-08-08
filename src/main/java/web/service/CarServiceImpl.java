package web.service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {
    @Override
    public List<Car> getCars(int amount) {

        List<Car> cars = new ArrayList<Car>();
        cars.add(new Car(1, "Mitsubishi", "L200"));
        cars.add(new Car(2, "Mitsubishi", "Pajero"));
        cars.add(new Car(3, "Mitsubishi", "Pajero Sport"));
        cars.add(new Car(4, "Mitsubishi", "Lancer"));
        cars.add(new Car(5, "Mitsubishi", "Outlander"));

        if (amount >= cars.size()) {
            return cars;
        }

        List<Car> returningList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            returningList.add(cars.get(i));
        }
        return returningList;
    }
}
