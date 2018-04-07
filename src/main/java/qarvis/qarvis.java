package qarvis;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.exceptions.*;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.*;

public class qarvis extends ListenerAdapter {

    public static JDA api;

    public static void main(String[] args) {
    		
    		try {
    			api = new JDABuilder(AccountType.BOT).setToken(Ref.TOKEN).buildBlocking();
    			//api.getPresence().setGame(Game.of(null, "hugs all around!"));
    			api.addEventListener(new Commands()); 
    			api.addEventListener(new qarvis());
    		} catch (LoginException | IllegalArgumentException | InterruptedException e) {
    			e.printStackTrace();
    		}
    }
    
    public void onGuildJoinEvent(GuildJoinEvent e) {
    		System.out.printf("[+] %s (%s Members)\n", e.getGuild().getName(), e.getGuild().getMembers().size());
    }

}