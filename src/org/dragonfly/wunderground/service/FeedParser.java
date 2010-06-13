package org.dragonfly.wunderground.service;

import java.util.List;

import org.dragonfly.wunderground.exception.DragonflySaxException;

public interface FeedParser {
    List<?> parse() throws DragonflySaxException;
}
