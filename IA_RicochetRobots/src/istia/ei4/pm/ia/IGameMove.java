package istia.ei4.pm.ia;

/**
 *
 * @author usrlocal
 */
public interface IGameMove {
  
  public AGameState apply(AGameState state, AWorld world);
  
}
