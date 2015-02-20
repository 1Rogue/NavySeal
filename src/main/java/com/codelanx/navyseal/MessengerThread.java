/*
 * Copyright (C) 2015 Spencer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.codelanx.navyseal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.bukkit.entity.Player;

/**
 * Class description for {@link MessengerThread}
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public class MessengerThread extends Thread {

    //do I even care
    public volatile boolean running = true;
    private final Queue<PlayerWordPair> queue = new LinkedList<>();
    private static final List<String> messages = new ArrayList<String>() {{
        add("What the %s did you just fucking %s about me, you little %s?");
        add("I'll have you know I graduated top of my %s in the Navy %ss");
        add("and I've been involved in numerous secret %ss on Al-%s, and I have over 300 confirmed %ss.");
        add("I am trained in %s warfare and I'm the top %s in the entire US armed %s.");
        add("You are nothing to me but just another %s.");
        add("I will %s you the fuck out with %s the likes of which has never been seen before");
        add("on this %s, mark my fucking %s.");
        add("You think you can get away with saying that %s to me over the %s?");
        add("Think again, %s.");
        add("As we speak I am contacting my secret network of %s across the USA");
        add("our %s is being traced right now so you better prepare for the spam, maggot.");
        add("The spam that wipes out the pathetic little thing you call your %s.");
        add("You're fucking %s, kid.");
        add("I can be %s, %s, and I can %s you in over %s ways");
        add("and that's just with my bare %s.");
        add("Not only am I extensively trained in unarmed %s,");
        add("but I have access to the entire arsenal of the United States %s");
        add("I will use it to its full extent to wipe your miserable %s off the face of the %s, you little %s.");
        add("If only you could have known what unholy %s your little “%s” comment was about to bring down upon you");
        add("maybe you would have held your fucking %s.");
        add("But you %s, you %s, and now you're %s, you goddamn %s.");
        add("I will shit %s all over you and you will drown in it.");
        add("You're fucking %s, kiddo.");
    }};

    public synchronized void queue(PlayerWordPair pw) {
        this.queue.add(pw);
    }

    @Override
    public void run() {
        do {
            PlayerWordPair pw = this.queue.poll();
            if (pw == null) {
                continue;
            }
            Player p = pw.getPlayer();
            if (p == null) {
                continue;
            }
            MessengerThread.messages.forEach(s -> this.message(p, s, pw));
        } while(running);
    }

    private void message(Player p, String message, PlayerWordPair pw) {
        p.chat(message.replace("%s", pw.getWord()));
        try {
            Thread.sleep((long) (pw.getRate() * message.length()));
        } catch (InterruptedException ex) {
            System.err.println("Whelp");
        }
    }
}
