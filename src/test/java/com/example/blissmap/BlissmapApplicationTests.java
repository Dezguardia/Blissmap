package com.example.blissmap;

import com.example.blissmap.Controllers.MapController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import com.example.blissmap.Models.SearchResult;
import com.example.blissmap.Services.TomTomService;

import java.util.List;

class MapControllerTest {

	@Test
	void testHomePage() {
		MapController mapController = new MapController();

		Model model = mock(Model.class);
		String viewName = mapController.homePage(model);

		assertEquals("map", viewName);
	}
}


@SpringBootTest
class BlissmapApplicationTests {

	@Autowired
	private TomTomService tomTomService;  // Inject the actual TomTomService instance

	@Test
	void testTomTomServiceSearchSpas() {

		// Performing the search
		List<SearchResult> searchResults = tomTomService.searchSpas(48.422030344484135, -71.04787632479426, 10000);

		// Asserting that the result is not null and contains at least one result
		assertNotNull(searchResults);
		assertFalse(searchResults.isEmpty());
	}

	@Test
	void contextLoads() {
	}

}



