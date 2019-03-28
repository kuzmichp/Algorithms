package tickets;

class Solution {

    public int mincostTickets(int[] days, int[] costs) {
        int firstDay = days[0];
        int lastDay = days[days.length - 1];

        int[] minimumCostsSoFar = new int[lastDay + 1];
        minimumCostsSoFar[firstDay] = costs[0];

        for (int i = 1; i < days.length; i++) {
            int travelDay = days[i];
            int previousTravelDay = days[i - 1];

            // From previous travel minimum cost hasn't changed
            updateCosts(minimumCostsSoFar, previousTravelDay, travelDay, minimumCostsSoFar[previousTravelDay]);

            int minimumCostSoFar = minimumCostsSoFar[previousTravelDay] + costs[0];
            minimumCostsSoFar[travelDay] = minimumCostSoFar;

            int weekAgo = travelDay - 7 + 1;
            int moneySpentBuyingWeeklyTicket = weekAgo >= firstDay ? minimumCostsSoFar[weekAgo - 1] + costs[1] : costs[1];
            if (moneySpentBuyingWeeklyTicket < minimumCostSoFar) {
                minimumCostSoFar = moneySpentBuyingWeeklyTicket;
                updateCosts(minimumCostsSoFar, weekAgo, travelDay, minimumCostSoFar);
            }

            int monthAgo = travelDay - 30 + 1;
            int moneySpentBuyingMonthlyTicket = monthAgo >= firstDay ? minimumCostsSoFar[monthAgo - 1] + costs[2] : costs[2];
            if (moneySpentBuyingMonthlyTicket < minimumCostSoFar) {
                minimumCostSoFar = moneySpentBuyingMonthlyTicket;
                updateCosts(minimumCostsSoFar, monthAgo, travelDay, minimumCostSoFar);
            }
        }
        return minimumCostsSoFar[lastDay];
    }

    private void updateCosts(int[] minimumCostsSoFar, int from, int to, int cost) {
        from = from < 1 ? 1 : from;
        for (int j = from; j <= to; j++) {
            minimumCostsSoFar[j] = cost;
        }
    }
}
