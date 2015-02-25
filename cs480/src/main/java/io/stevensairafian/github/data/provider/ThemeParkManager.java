package io.stevensairafian.github.data.provider;

import java.util.List;
import io.stevensairafian.github.data.ThemePark;

public interface ThemeParkManager {

    public List<ThemePark> searchParks(String keyword);
}

	
