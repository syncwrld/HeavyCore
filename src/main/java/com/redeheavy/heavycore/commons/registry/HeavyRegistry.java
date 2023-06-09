package com.redeheavy.heavycore.commons.registry;

import com.redeheavy.heavycore.commons.Basement;
import com.redeheavy.heavycore.commons.logging.LoggerFactory;
import com.redeheavy.heavycore.commons.objects.HeavyPlugin;

public class HeavyRegistry extends RegistryMap {

    static final LoggerFactory logger = Basement.getLogger();
    private final HeavyPlugin heavyPlugin;

    public HeavyRegistry(HeavyPlugin heavyPlugin) {
        this.heavyPlugin = heavyPlugin;

        String author = heavyPlugin.getAuthors() == null ? heavyPlugin.getAuthor() : (heavyPlugin.getAuthors()).toString();

        logger.success("Registrado plugin: [" +
                heavyPlugin.getPluginName() + "] - versão " + heavyPlugin.getPluginVersion()
                + " by " + author
        );

        addValid(heavyPlugin);
    }

    @Deprecated
    public void register() {
        logger.warn("O plugin " + heavyPlugin.getPluginName() + " utiliza o método HeavyRegistry#register para fazer o registro do plugin no Core. O método #register está deprecado na nova versão do Core, ele já é feito automaticamente ao criar um HeavyPlugin, peça ao autor para remover a liha que registra usando diretamente a classe HeavyRegistry.");
        addValid(heavyPlugin);
    }

}
