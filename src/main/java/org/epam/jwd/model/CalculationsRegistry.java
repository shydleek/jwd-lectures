package org.epam.jwd.model;

import java.math.BigDecimal;
import java.util.Objects;

public class CalculationsRegistry {

    private final Integer id;
    private BigDecimal angleToXAxis;
    private BigDecimal angleToYAxis;
    private BigDecimal angleToZAxis;
    private boolean isPlane;
    private boolean isPerpendicularToXAxis;
    private boolean isPerpendicularToYAxis;
    private boolean isPerpendicularToZAxis;

    public CalculationsRegistry(Integer id, BigDecimal angleToXAxis, BigDecimal angleToYAxis, BigDecimal angleToZAxis,
                                boolean isPlane, boolean isPerpendicularToXAxis, boolean isPerpendicularToYAxis,
                                boolean isPerpendicularToZAxis) {
        this.id = id;
        this.angleToXAxis = angleToXAxis;
        this.angleToYAxis = angleToYAxis;
        this.angleToZAxis = angleToZAxis;
        this.isPlane = isPlane;
        this.isPerpendicularToXAxis = isPerpendicularToXAxis;
        this.isPerpendicularToYAxis = isPerpendicularToYAxis;
        this.isPerpendicularToZAxis = isPerpendicularToZAxis;
    }

    public CalculationsRegistry withId(Integer id) {
        return new CalculationsRegistry(id, this.angleToXAxis, this.angleToYAxis, this.angleToZAxis, this.isPlane,
                this.isPerpendicularToXAxis, this.isPerpendicularToYAxis, this.isPerpendicularToZAxis);
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getAngleToXAxis() {
        return angleToXAxis;
    }

    public void setAngleToXAxis(BigDecimal angleToXAxis) {
        this.angleToXAxis = angleToXAxis;
    }

    public BigDecimal getAngleToYAxis() {
        return angleToYAxis;
    }

    public void setAngleToYAxis(BigDecimal angleToYAxis) {
        this.angleToYAxis = angleToYAxis;
    }

    public BigDecimal getAngleToZAxis() {
        return angleToZAxis;
    }

    public void setAngleToZAxis(BigDecimal angleToZAxis) {
        this.angleToZAxis = angleToZAxis;
    }

    public boolean isPlane() {
        return isPlane;
    }

    public void setPlane(boolean plane) {
        isPlane = plane;
    }

    public boolean isPerpendicularToXAxis() {
        return isPerpendicularToXAxis;
    }

    public void setPerpendicularToXAxis(boolean perpendicularToXAxis) {
        isPerpendicularToXAxis = perpendicularToXAxis;
    }

    public boolean isPerpendicularToYAxis() {
        return isPerpendicularToYAxis;
    }

    public void setPerpendicularToYAxis(boolean perpendicularToYAxis) {
        isPerpendicularToYAxis = perpendicularToYAxis;
    }

    public boolean isPerpendicularToZAxis() {
        return isPerpendicularToZAxis;
    }

    public void setPerpendicularToZAxis(boolean perpendicularToZAxis) {
        isPerpendicularToZAxis = perpendicularToZAxis;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CalculationsRegistry that = (CalculationsRegistry) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CalculationsRegistry{" +
                "id=" + id +
                ", angleToXAxis=" + angleToXAxis +
                ", angleToYAxis=" + angleToYAxis +
                ", angleToZAxis=" + angleToZAxis +
                ", isPlane=" + isPlane +
                ", isPerpendicularToXAxis=" + isPerpendicularToXAxis +
                ", isPerpendicularToYAxis=" + isPerpendicularToYAxis +
                ", isPerpendicularToZAxis=" + isPerpendicularToZAxis +
                '}';
    }
}