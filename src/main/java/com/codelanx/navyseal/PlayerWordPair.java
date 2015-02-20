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

import java.lang.ref.WeakReference;
import org.bukkit.entity.Player;

/**
 * Class description for {@link PlayerWordPair}
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public final class PlayerWordPair {

    private final WeakReference<Player> p;
    private final String word;
    private final double rate;
    public static final int DEFAULT_WPM = 75;

    public PlayerWordPair(Player p, String word) {
        this(p, word, PlayerWordPair.DEFAULT_WPM);
    }

    public PlayerWordPair(Player p, String word, int wpm) {
        this.p = new WeakReference<>(p);
        this.word = word;
        this.rate = (1000D / (wpm * 5) * 60); //WPM -> CPM -> CPS etc
    }

    public Player getPlayer() {
        return this.p.get();
    }

    public String getWord() {
        return this.word;
    }

    public double getRate() {
        return this.rate;
    }

}
