import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface CharacterProperties {
    void display(String content);
}

// Concrete Flyweight class
class CharacterPropertiesImpl implements CharacterProperties {
    private String font;
    private String color;
    private int size;

    public CharacterPropertiesImpl(String font, String color, int size) {
        this.font = font;
        this.color = color;
        this.size = size;
    }

    public void display(String content) {
        System.out.println("" + content+ ", Font: " + font +  ", Color: " + color + ", Size: " + size);
    }
}

// Flyweight Factory class
class CharacterPropertiesFactory {
    private Map<String, CharacterProperties> characterPropertiesCache = new HashMap<>();

    public CharacterProperties getCharacterProperties(String font, String color, int size) {
        String key = font + color + size;
        if (!characterPropertiesCache.containsKey(key)) {
            characterPropertiesCache.put(key, new CharacterPropertiesImpl(font, color, size));
        }
        return characterPropertiesCache.get(key);
    }
}

// Context class
class Document {
    private CharacterPropertiesFactory characterPropertiesFactory = new CharacterPropertiesFactory();
    private Map<Integer, CharacterProperties> characters = new HashMap<>();

    public void addCharacter(int position, String content, String font, String color, int size) {
        CharacterProperties characterProperties = characterPropertiesFactory.getCharacterProperties(font, color, size);
        characters.put(position, characterProperties);
        System.out.println("Added character at position " + position + " with Content: " + content + " Font: " + font + " Color: " + color+ " Size: " + size );

    }

    public void saveToFile(String fileName) {
        // Save characters content to file
        System.out.println("\nSaved document to file: " + fileName);
    }

    public void loadFromFile(String fileName) {
        System.out.println("Loaded document from file: " + fileName);
    }

    public void displayCharacters() {
        // Display characters with their properties
        for (Map.Entry<Integer, CharacterProperties> entry : characters.entrySet()) {
            Integer position = entry.getKey();
            CharacterProperties characterProperties = entry.getValue();
            characterProperties.display( "Character at position " + position);

        }
    }

}
public class TextEditor {
    public static void main(String[] args) {
        Document document = new Document();

        // Add characters to the document
        document.addCharacter(0, "H", "Arial", "Red", 12);
        document.addCharacter(1, "e", "Calibri", "Red", 12);
        document.addCharacter(2, "l", "Arial", "Blue", 14);
        document.addCharacter(3, "y", "Calibri", "Blue", 14);
        document.addCharacter(4, "W", "Verdana", "Black", 16);
        document.addCharacter(5, "o", "Verdana", "Black", 16);


        // Save document to file
        document.saveToFile("HelloWorldCS5800.txt");

        // Display characters in the document
        System.out.println("\nDisplaying Saved file Content");
        document.displayCharacters();

        // Load document from file
        Document loadedDocument = new Document();
        loadedDocument.loadFromFile("HelloWorldCS5800.txt");
        loadedDocument.displayCharacters();
    }
}

