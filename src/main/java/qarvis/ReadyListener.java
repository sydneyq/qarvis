package qarvis;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.Invite.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.entities.*;

public class ReadyListener extends ListenerAdapter{
	
	public void onReady(ReadyEvent e) {
		String out = "\nbeepboop! running on: \n";
		
		for (net.dv8tion.jda.core.entities.Guild g : e.getJDA().getGuilds()) {
			out += g.getName() + " (" + g.getId() + ") \n";
		}
		
		System.out.println(out);
	}

}
