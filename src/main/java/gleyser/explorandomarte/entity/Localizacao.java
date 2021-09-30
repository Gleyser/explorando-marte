package gleyser.explorandomarte.entity;

import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
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

	public void incrementaCoordenadaX(){
		this.coordenadaX++;
	}

	public void incrementaCoordenadaY(){
		this.coordenadaY++;
	}

	public void decrementaCoordenadaX(){
		this.coordenadaX--;
	}

	public void decrementaCoordenadaY(){
		this.coordenadaY--;
	}

	@Override
	public boolean equals(Object o) {
		Localizacao that = (Localizacao) o;
		return this.getCoordenadaX().equals(that.getCoordenadaX())
				&& this.getCoordenadaY().equals((that.getCoordenadaY()));


	}

	@Override
	public int hashCode() {
		int result = coordenadaX.hashCode();
		result = 31 * result + coordenadaY.hashCode();
		return result;
	}
}
