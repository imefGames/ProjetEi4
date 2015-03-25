package istia.ei4.ProjetISTIA;

/**
 * Created by Pierre on 04/03/2015.
 */
public class GameMouvementInterface implements IGameObject {

    private float[] scales;
    private int img_up          = R.drawable.img_int_up;
    private int img_down        = R.drawable.img_int_down;
    private int img_right       = R.drawable.img_int_right;
    private int img_left        = R.drawable.img_int_left;
    private boolean display     = false;
    private int x               = 0;
    private int y               = 0;
    private int minRadius       = 32;
    private int radius          = 150;
    private int decision        = -1;
    private GamePiece target;

    public GameMouvementInterface(){
        this.scales = new float[4];
        this.resetScale();
    }

    @Override
    public void create() {
    }

    @Override
    public void load(RenderManager renderManager) {
        renderManager.loadImage(img_up);
        renderManager.loadImage(img_down);
        renderManager.loadImage(img_right);
        renderManager.loadImage(img_left);
    }

    @Override
    public void draw(RenderManager renderManager) {
        if(this.display) {
            int diff = (int)(radius*this.scales[0]);
            renderManager.drawImage(x - diff, y - diff, x + diff, y, img_up); //haut
            diff = (int)(radius*this.scales[1]);
            renderManager.drawImage(x, y-diff, x+diff, y+diff, img_right); //droite
            diff = (int)(radius*this.scales[2]);
            renderManager.drawImage(x-diff, y, x+diff, y+diff, img_down); //bas
            diff = (int)(radius*this.scales[3]);
            renderManager.drawImage(x-diff, y-diff, x, y+diff, img_left); //gauche
        }
    }

    @Override
    public void update(GameManager gameManager) {
        if(this.display) {
            //mettre à jour les échelles de chaque option
            for(int i=0; i<4; i++){
                if(decision == i){
                    scales[i] += 0.05f;
                    if(scales[i] > 1.5f) {
                        scales[i] = 1.5f;
                    }
                }else {
                    scales[i] -= 0.05;
                    if (scales[i] < 1.0f) {
                        scales[i] = 1.0f;
                    }
                }
            }
            //si il y a des entrées utilisateur
            InputManager inputManager = gameManager.getInputManager();
            if(inputManager.eventHasOccurred()){
                int xTouch, yTouch;
                xTouch = (int)inputManager.getTouchX();
                yTouch = (int)inputManager.getTouchY();
                //si l'utilisateur relâche l'écran, ...
                if(inputManager.upOccurred()){
                    this.display = false;
                    //si il y a une décision, ...
                    if(this.decision >=0){
                        //acquérir & transmettre destination du pion
                        ((GridGameScreen)(gameManager.getCurrentScreen())).editDestination(target, this.decision);
                    }
                }else if(inputManager.moveOccurred()){ //sinon, si l'utilisateur bouge son doigt, gérer déplacement...
                    int dx, dy;
                    dx = xTouch - x;
                    dy = yTouch - y;
                    //si le doigt est dans le pion, ...
                    if(dx*dx + dy*dy < minRadius*minRadius){
                        //plus de décision
                        decision = -1;
                    }else{//sinon, ...
                        //si la décision est horizontale, ...
                        if(dx*dx >dy*dy){
                            //si la décision est gauche, ...
                            if(dx < 0){
                                decision = 3;
                            }else{ //sinon, ...
                                decision = 1;
                            }
                        }else{ //sinon, ...
                            //si la décision est haut, ...
                            if(dy < 0){
                                decision = 0;
                            }else{ //sinon, ...
                                decision = 2;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void destroy() {
    }

    public void setPosition(int x, int y){
        this.decision = -1;
        this.x = x;
        this.y = y;
    }

    public void enable(boolean display){
        this.resetScale();
        this.display = display;
    }

    private void resetScale(){
        for(int i=0; i<4; i++){
            this.scales[i] = 1.0f;
        }
    }

    public void setTarget(GamePiece p){
        target = p;
    }
}
