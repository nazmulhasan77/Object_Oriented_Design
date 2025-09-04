package Flyweight;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DemoSingleFile extends JFrame {
    // === Flyweight shared class ===
    static class TreeType {
        String name;
        Color color;
        String otherTreeData;

        public TreeType(String name, Color color, String otherTreeData) {
            this.name = name;
            this.color = color;
            this.otherTreeData = otherTreeData;
        }

        public void draw(Graphics g, int x, int y) {
            g.setColor(Color.BLACK);
            g.fillRect(x - 1, y, 3, 5);
            g.setColor(color);
            g.fillOval(x - 5, y - 10, 10, 10);
        }
    }

    // === Tree with extrinsic state ===
    static class Tree {
        int x, y;
        TreeType type;

        public Tree(int x, int y, TreeType type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public void draw(Graphics g) {
            type.draw(g, x, y);
        }
    }

    // === TreeType factory (flyweight factory) ===
    static class TreeFactory {
        static Map<String, TreeType> treeTypes = new HashMap<>();

        public static TreeType getTreeType(String name, Color color, String otherTreeData) {
            String key = name + color.toString() + otherTreeData;
            TreeType result = treeTypes.get(key);
            if (result == null) {
                result = new TreeType(name, color, otherTreeData);
                treeTypes.put(key, result);
            }
            return result;
        }
    }

    // === Forest rendering ===
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color, String otherTreeData) {
        TreeType type = TreeFactory.getTreeType(name, color, otherTreeData);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    @Override
    public void paint(Graphics graphics) {
        for (Tree tree : trees) {
            tree.draw(graphics);
        }
    }

    // === Main method ===
    public static void main(String[] args) {
        int CANVAS_SIZE = 500;
        int TREES_TO_DRAW = 1000000;
        int TREE_TYPES = 2;

        DemoSingleFile forest = new DemoSingleFile();
        for (int i = 0; i < TREES_TO_DRAW / TREE_TYPES; i++) {
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "Summer Oak", Color.GREEN, "Oak texture stub");
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "Autumn Oak", Color.ORANGE, "Autumn Oak texture stub");
        }

        forest.setSize(CANVAS_SIZE, CANVAS_SIZE);
        forest.setDefaultCloseOperation(EXIT_ON_CLOSE);
        forest.setVisible(true);

        System.out.println(TREES_TO_DRAW + " trees drawn");
        System.out.println("---------------------");
        System.out.println("Memory usage:");
        System.out.println("Tree size (8 bytes) * " + TREES_TO_DRAW);
        System.out.println("+ TreeTypes size (~30 bytes) * " + TREE_TYPES);
        System.out.println("---------------------");
        System.out.println("Total: " + ((TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024) +
                "MB (instead of " + ((TREES_TO_DRAW * 38) / 1024 / 1024) + "MB)");
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
