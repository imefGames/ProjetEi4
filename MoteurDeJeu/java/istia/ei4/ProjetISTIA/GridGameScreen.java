package istia.ei4.ProjetISTIA;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alain on 25/02/2015.
 */
public class GridGameScreen extends GameScreen {
    private boolean randomGrid = false;
    private Canvas canvasGrid;
    private Dictionary dict;
    private ArrayList gridElements;
    private int imageGridID;
    private boolean imageLoaded = false;

    private int xGrid = 0;
    private int yGrid = 100;

    private float gridSpace = 0;
    private int margin = 0;

    private GameMouvementInterface gmi;
    private Bitmap bitmapGrid;
    RenderManager currentRenderManager;
    Map<String, Drawable> drawables = new HashMap<String, Drawable>();
    Map<String, Integer> colors = new HashMap<String, Integer>();


    public GridGameScreen(GameManager gameManager){
        super(gameManager);

        gridSpace = (float)(67.5 * gameManager.getScreenWidth() /1080);
        xGrid = 0;
        yGrid = 1080/5;


        Bitmap.Config conf = Bitmap.Config.ARGB_4444;

        bitmapGrid = Bitmap.createBitmap((int)(16 * gridSpace), (int) (16 * gridSpace), conf);
        canvasGrid = new Canvas(bitmapGrid);
        currentRenderManager = gameManager.getRenderManager();
    }

    @Override
    public void create()
    {
//        this.instances.add(new GamePiece(0, 0, Color.RED));
        gmi = new GameMouvementInterface();
        this.instances.add(gmi);
    }

    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);

    }

    @Override
    public void draw(RenderManager renderManager)
    {
        renderManager.setColor(Color.argb(255, 175, 167, 123));
        renderManager.paintScreen();

        if(imageLoaded)
        {
            gameManager.getRenderManager().drawImage(xGrid, yGrid, (int)(16*gridSpace) + xGrid, (int)(16*gridSpace) + yGrid,  imageGridID);

//            for (Object element : gridElements) {
//                GridElement myp = (GridElement) element;
//
//                if (myp.getType() == "rr" || myp.getType() == "rv" || myp.getType() == "rj" || myp.getType() == "rb") {
//                    drawables.get(myp.getType()).setBounds((int)(myp.getX() * gridSpace),(int)(myp.getY() * gridSpace), (int)((myp.getX() + 1) * gridSpace), (int)((myp.getY()+1) * gridSpace));
//                    drawables.get(myp.getType()).draw(canvasGrid);
//                }
//            }
        }
        super.draw(renderManager);
    }

    public void setRandomGame(boolean random)
    {
        randomGrid = random;

        MapGenerator generatedMap = new MapGenerator();
        gridElements = generatedMap.get16DimensionalMap();

//        MapObjects mapobjects = new MapObjects();
//        FileReadWrite file = new FileReadWrite();
//        file.write("/sdcard/generatedMap.txt", mapobjects.createStringFromList(gridElements));


        createGrid();

    }

    public void createGrid()
    {

        currentRenderManager.setTarget(canvasGrid);

        drawables.put("grid", currentRenderManager.getResources().getDrawable(R.drawable.grid));
        drawables.put("mh", currentRenderManager.getResources().getDrawable(R.drawable.mh));
        drawables.put("mv", currentRenderManager.getResources().getDrawable(R.drawable.mv));

        drawables.put("rv", currentRenderManager.getResources().getDrawable(R.drawable.rv));
        drawables.put("rr", currentRenderManager.getResources().getDrawable(R.drawable.rr));
        drawables.put("rj", currentRenderManager.getResources().getDrawable(R.drawable.rj));
        drawables.put("rb", currentRenderManager.getResources().getDrawable(R.drawable.rb));

        drawables.put("cv", currentRenderManager.getResources().getDrawable(R.drawable.cv));
        drawables.put("cr", currentRenderManager.getResources().getDrawable(R.drawable.cr));
        drawables.put("cj", currentRenderManager.getResources().getDrawable(R.drawable.cj));
        drawables.put("cb", currentRenderManager.getResources().getDrawable(R.drawable.cb));
        drawables.put("cm", currentRenderManager.getResources().getDrawable(R.drawable.cm));


        drawables.get("grid").setBounds(0, 0,(int)( 16 * gridSpace),(int)( 16 * gridSpace));
        drawables.get("grid").draw(canvasGrid);

        for (Object element : gridElements) {
            GridElement myp = (GridElement) element;

            if (myp.getType() == "cr" || myp.getType() == "cv" || myp.getType() == "cj" || myp.getType() == "cb" || myp.getType() == "cm") {
                drawables.get(myp.getType()).setBounds((int)(myp.getX() * gridSpace),(int)( myp.getY() * gridSpace),(int)( (myp.getX() + 1) * gridSpace),(int)( (myp.getY()+1) * gridSpace));
                drawables.get(myp.getType()).draw(canvasGrid);
            }
        }

        for (Object element : gridElements) {
            GridElement myp = (GridElement) element;

            if (myp.getType() == "mh") {
                drawables.get("mh").setBounds((int)(myp.getX() * gridSpace), (int)(myp.getY() * gridSpace -1), (int)((myp.getX() + 1) * gridSpace), (int)(myp.getY() * gridSpace + 1));
                drawables.get("mh").draw(canvasGrid);
            }

            if (myp.getType() == "mv") {
                drawables.get("mv").setBounds((int)(myp.getX() * gridSpace-1), (int)(myp.getY() * gridSpace), (int)(myp.getX() * gridSpace + 1), (int)((myp.getY() + 1) * gridSpace));
                drawables.get("mv").draw(canvasGrid);
            }

        }

        currentRenderManager.resetTarget();


        //On supprime l'image de fond si elle existe et on sauvegarde celle que l'on vient de créer
        if(imageLoaded == true)
        {
            currentRenderManager.unloadBitmap(imageGridID);
        }
        imageGridID = currentRenderManager.loadBitmap(bitmapGrid);
        imageLoaded = true;

        System.out.println("Fin de createGrid");

        createRobots();

    }

    public void createRobots()
    {
        colors.put("rr", Color.RED);
        colors.put("rb", Color.BLUE);
        colors.put("rv", Color.GREEN);
        colors.put("rj", Color.YELLOW);

        colors.put("cr", Color.RED);
        colors.put("cb", Color.BLUE);
        colors.put("cv", Color.GREEN);
        colors.put("cj", Color.YELLOW);

        for(Object currentObject : this.instances)
        {
            if(currentObject.getClass() == GamePiece.class)
            {
                this.instances.remove(currentObject);
            }
        }

        for (Object element : gridElements) {
                GridElement myp = (GridElement) element;

                if (myp.getType() == "rr" || myp.getType() == "rv" || myp.getType() == "rj" || myp.getType() == "rb") {

                    GamePiece currentPiece = new GamePiece(myp.getX(), myp.getY(), colors.get(myp.getType()));
                    currentPiece.setGridDimensions(xGrid, yGrid, gridSpace);

                    this.instances.add(currentPiece);
                }
            }
    }

    public void activateInterface(GamePiece p, int x, int y){
        gmi.enable(true);
        gmi.setPosition(x-1, y);
        gmi.setTarget(p);
    }

    public void editDestination(GamePiece p, int direction)
    {
        int xDestination = p.getxObjective();
        int yDestination = p.getyObjective();

        boolean canMove = true;



        for(Object instance : this.instances)
        {
            if(instance.getClass() == p.getClass() && p != instance && canMove)
            {
                switch(direction){
                    case 0:     // haut
                        canMove = collision((GamePiece) instance, xDestination, yDestination - 1, canMove);
                        break;
                    case 1:     // droite
                        canMove = collision((GamePiece) instance, xDestination+1, yDestination, canMove);
                        break;
                    case 2:     // bas
                        canMove = collision((GamePiece) instance, xDestination, yDestination + 1, canMove);
                        break;
                    case 3:     // gauche
                        canMove = collision((GamePiece) instance, xDestination-1, yDestination, canMove);
                        break;
                }
            }
        }
//        System.out.println("CanMove Robot");
//        System.out.println(canMove);


        for (Object element : gridElements) {
            GridElement myp = (GridElement) element;

            if ((myp.getType() == "mv") && (direction == 1)) {  // droite
                canMove = collision(p, myp.getX() - 1, myp.getY(), canMove);
            }
            if ((myp.getType() == "mv") && (direction == 3)) {  // gauche
                canMove = collision(p, myp.getX(), myp.getY(), canMove);
            }

            if ((myp.getType() == "mh") && (direction == 0)) {  // haut
                canMove = collision(p, myp.getX(), myp.getY(), canMove);
            }
            if ((myp.getType() == "mh") && (direction == 2)) {  // bas
                canMove = collision(p, myp.getX(), myp.getY() - 1, canMove);
            }
        }

//        System.out.println("CanMove Walls");
//        System.out.println(canMove);

        if(canMove)
        {
            switch(direction){
                case 0:     // haut
                    yDestination -= 1;
                    break;
                case 1:     // droite
                    xDestination +=1;
                    break;
                case 2:     // bas
                    yDestination += 1;
                    break;
                case 3:     // gauche
                    xDestination -=1;
                    break;
            }
            p.setxObjective(xDestination);
            p.setyObjective(yDestination);

            editDestination(p, direction);
        }

        boolean b = gagne(p);

    }

    public boolean gagne(GamePiece p)
    {
        System.out.println("test si gagne");

        for (Object element : gridElements) {
            GridElement myp = (GridElement) element;
            {
                if (myp.getType().equals("cm") && myp.getX() == p.getX() && myp.getY() == p.getY())
                {
                    System.out.println("GAGNE !!!");
                    return true;
                }
                else if((myp.getX() == p.getX()) && (myp.getY() == p.getY()) && (myp.getType().equals("cr") || myp.getType().equals("cv") || myp.getType().equals("cb") || myp.getType().equals("cj")))
                {

                    if(p.getColor() == colors.get((myp.getType())))
                    {
                        System.out.println("GAGNE !!!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean collision(GamePiece p, int x, int y, boolean canMove)
    {
        if(p.getxObjective() == x && p.getyObjective() == y && canMove == true)
            return false;
        else if(canMove == false)
            return false;
        return true;
    }
}
