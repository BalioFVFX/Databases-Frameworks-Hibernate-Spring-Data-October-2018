package app.domain.dto.exportDto;

import java.util.List;

public class Query4Dto {
    private Query4CarDto car;
    private List<Query4PartsDto> parts;

    public Query4Dto() {
    }

    public Query4CarDto getCar() {
        return car;
    }

    public void setCar(Query4CarDto car) {
        this.car = car;
    }

    public List<Query4PartsDto> getParts() {
        return parts;
    }

    public void setParts(List<Query4PartsDto> parts) {
        this.parts = parts;
    }
}
