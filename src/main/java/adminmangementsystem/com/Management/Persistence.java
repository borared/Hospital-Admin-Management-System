package adminmangementsystem.com.Management;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Persistence {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static <T> List<T> loadList(String filePath, Type typeOfT) {
        try {
            Path p = Paths.get(filePath);
            if (!Files.exists(p)) return new ArrayList<>();
            String json = new String(Files.readAllBytes(p), StandardCharsets.UTF_8);
            List<T> list = GSON.fromJson(json, typeOfT);
            return list == null ? new ArrayList<>() : list;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static <T> void saveList(List<T> list, String filePath) {
        try {
            Path p = Paths.get(filePath);
            Path parent = p.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            String json = GSON.toJson(list);
            Files.write(p, json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
