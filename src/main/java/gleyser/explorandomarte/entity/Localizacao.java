package gleyser.explorandomarte.entity;

import javax.persistence.*;

@Entity
public class Localizacao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	private Integer coordenadaX;
	
	@Column(nullable = false)
	private Integer coordenadaY;

	public Long getId() {
		return id;
	}

	public Integer getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(Integer coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public Integer getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(Integer coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Localizacao that = (Localizacao) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (coordenadaX != null ? !coordenadaX.equals(that.coordenadaX) : that.coordenadaX != null) return false;
		return coordenadaY != null ? coordenadaY.equals(that.coordenadaY) : that.coordenadaY == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (coordenadaX != null ? coordenadaX.hashCode() : 0);
		result = 31 * result + (coordenadaY != null ? coordenadaY.hashCode() : 0);
		return result;
	}
}
