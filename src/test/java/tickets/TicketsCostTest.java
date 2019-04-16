package tickets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TicketsCostTest {

	private final TicketsCost ticketsCost = new TicketsCost();

	@DataProvider(name = "travelDataProvider")
	private Object[][] getTravelData() {
		return new Object[][] {
				{ new int[] { 1, 4, 6, 7, 8, 20 }, new int[] { 2, 7, 15 }, 11 },
				{ new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 }, new int[] { 2, 7, 15 }, 17 }
		};
	}

	@Test(dataProvider = "travelDataProvider")
	public void shouldComputeMinimumCost(int[] days, int[] costs, int expectedMinimumCost) {
		// when
		int minimumTicketsCost = ticketsCost.minCostTickets(days, costs);

		// then
		assertThat(minimumTicketsCost)
				.as("Minimum tickets cost is the same as expected")
				.isEqualTo(expectedMinimumCost);
	}
}
