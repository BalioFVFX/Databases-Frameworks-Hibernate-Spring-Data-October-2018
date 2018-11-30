package app.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "part_suppliers")
public class PartSupplier extends BaseEntity {
    private String name;
    private boolean isImporter;
    private List<Part> parts;

    public PartSupplier() {
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "uses_imported_parts")
    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }


    @OneToMany(targetEntity = Part.class, mappedBy = "partSupplier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
