package top.whitecola.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.function.Function;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.entity.Player;

public class HiLanguage {
    public interface ResourceGetter {
        URL getResource(String path);
    }

    private final String namespace;
    private final ResourceGetter resourceGetter;

    public HiLanguage(String namespace, ResourceGetter resourceGetter) {
        this.namespace = namespace;
        this.resourceGetter = resourceGetter;
    }

    public String of(Player player, String key) {
        try {
            String locale = player.getLocale();
            InputStreamReader resource = new InputStreamReader(resourceGetter.getResource("assets/" + namespace + "/lang/" + locale + ".json").openStream());
            Gson gson = new Gson();
            Type languageType = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> language = gson.fromJson(resource, languageType);
            resource.close();
            if(language.containsKey(key))
                return language.get(key);
            locale = "en_US"; //Set to default language;
            resource = new InputStreamReader(resourceGetter.getResource("assets/" + namespace + "/lang/" + locale + ".json").openStream());
            language = gson.fromJson(resource, languageType);
            resource.close();
            if(language.containsKey(key))
                return language.get(key);
        } catch (IOException e) {
            try {
                String locale = "en_US"; //Set to default language;
                InputStreamReader resource = new InputStreamReader(resourceGetter.getResource("assets/" + namespace + "/lang/" + locale + ".json").openStream());
                Type languageType = new TypeToken<Map<String, String>>(){}.getType();
                Gson gson = new Gson();
                Map<String, String> language = gson.fromJson(resource, languageType);
                resource.close();
                if(language.containsKey(key))
                    return language.get(key);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return key;
    }
}
