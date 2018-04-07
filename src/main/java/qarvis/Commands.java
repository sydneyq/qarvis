package qarvis;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;


public class Commands extends ListenerAdapter {
	static Queue que = new Queue(null);
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] command = event.getMessage().getContentRaw().split(" ");
		//String message = event.getMessage().getContent();
		
		if (!command[0].startsWith(Ref.PREFIX)) {
			return;
		}
		
		if (command[0].equalsIgnoreCase("+ping")) {
			String msg = "pong! `" + event.getJDA().getPing() + "ms`";
			event.getChannel().sendMessage(msg).queue();
		}
		
		if (command[0].equalsIgnoreCase("+add")){
			String msg = "-";
			if (!que.contains(event.getAuthor().getName())) {
				Node node = new Node(null, event.getAuthor().getName());
				que.enqueue(node);
				msg = "Added! :ok_hand:";
			}
			else {
				msg = "You're already in the queue!";
			}
			event.getChannel().sendMessage(msg).queue();
		}
		
		if (command[0].equalsIgnoreCase("+remove")) {
			String msg = "";
			if (que.peek().name.equals(event.getAuthor().getName())) {
				que.dequeue();
				msg = "\nUp next: `" + que.peek().name + "`";
			}
			else {
				que.delete(event.getAuthor().getName());
			}
			
			msg = "Successfully removed! :ok_hand:" + msg;
			event.getChannel().sendMessage(msg).queue();
			
			
		}
		
		if (command[0].equalsIgnoreCase("+queue")) {
			System.out.println("+queue has been called!");
			String msg = que.print();
			System.out.println("print() has executed!");
			if (msg == null) {
				msg = "No one is currently in the queue!";
			}
			
			EmbedBuilder eb = new EmbedBuilder();
			eb.setColor(Color.RED);
			eb.setDescription(msg);
			event.getChannel().sendMessage(eb.build()).queue();
			//event.getChannel().sendMessage(msg).queue();
		}
		
		if (command[0].equalsIgnoreCase("+restart")) {
			
		}
		
		if (command[0].equalsIgnoreCase("+skip")) {
			String msg = "";
			if (!event.getMember().hasPermission(Permission.VOICE_MUTE_OTHERS)
					|| que == null || que.tail == null) {
				msg += "Either\n"
						+ "1) you don't have permission to skip\n"
						+ "2) or there's no one to skip!";
			}
			else {
				msg += "`" + que.dequeue().name + "` skipped!";
				msg += "\nUp next: `" + que.peek().name + "`";
			}
			
			event.getChannel().sendMessage(msg).queue();
		}
		
		if (command[0].equalsIgnoreCase("+help")) {
			String msg = "";
			
			msg += "**__Commands!__**\n"
					+ "\t**MAIN**\n"
					+ "\t\t`+ping`\t - ping pong!\n"
					+ "\t\t`+info`\t - info about the bot!\n"
					+ "\t\t`+help`\t - all bot commands!\n\n"
					+ "\t**QUEUE**\n"
					+ "\t\t`+add`\t - adds you to the queue!\n"
					+ "\t\t`+remove`\t - removes you from the queue!\n"
					+ "\t\t`+queue`\t - prints the queue!\n"
					+ "\t\t`+skip`\t - skips the current person! (mods only.)";
			
			EmbedBuilder eb = new EmbedBuilder();
			eb.setColor(Color.CYAN);
			eb.setDescription(msg);
			event.getChannel().sendMessage(eb.build()).queue();
		}
		
		if (command[0].equalsIgnoreCase("+info")) {
			String msg = "Hello!\n"+
							"I'm qarvis, a friendly utility bot created by **sydneyq**.\n"
							+ "\n"
							+ "Need help with commands? Try saying **+help**!";
			
			EmbedBuilder eb = new EmbedBuilder();
			eb.setColor(Color.CYAN);
			eb.setDescription(msg);
			event.getChannel().sendMessage(eb.build()).queue();
		}
	}
	
	
}
