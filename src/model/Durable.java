/**
 * 
 */
package model;

/**
 * @author Bianca
 *
 */
public interface Durable {
	void avanzar(float inicioRelativo);
	void detener(float finRelativo);
}
