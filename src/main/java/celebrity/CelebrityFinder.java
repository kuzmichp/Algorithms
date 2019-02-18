package celebrity;

import java.util.ArrayList;
import java.util.List;

class CelebrityFinder {

    private RelationManager relationManager = new RelationManager();

    Person findCelebrity(List<Person> people) {
        List<Person> candidates = new ArrayList<>(people);
        int i = 0;
        while (i < candidates.size() - 1) {
            Person a = candidates.get(i);
            Person b = candidates.get(i + 1);
            boolean aKnowsB = relationManager.knows(a, b);
            boolean bKnowsA = relationManager.knows(b, a);
            if (aKnowsB && bKnowsA || !aKnowsB && !bKnowsA) {
                // None of them can be a celebrity
                candidates.remove(i + 1);
                candidates.remove(i);
            } else {
                // One of them might be a celebrity
                if (aKnowsB) {
                    candidates.remove(i);
                } else {
                    candidates.remove(i + 1);
                }
                i++;
            }
        }
        // TODO: Make sure that the candidate is a celebrity
        return candidates.isEmpty() ? null : candidates.get(0);
    }
}
