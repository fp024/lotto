package org.fp024.lotto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

/*
  1159회 도전 👍
    중복된 것 우선순위 적용: [17, 22, 23, 25, 31, 42]
    중복되지 않은 것 우선순위 적용: [26, 27, 28, 32, 33, 35]
    완전 랜덤 01: [18, 23, 25, 27, 39, 42]
    완전 랜덤 02: [17, 18, 25, 27, 33, 35]
    완전 랜덤 03: [5, 18, 25, 31, 33, 35]
*/
class LottoTests {
  @Test
  void testPrioritizeDuplicates() {
    generateAndPrintLottoNumbers();
  }

  void generateAndPrintLottoNumbers() {
    String slotA = "5  10  26  34  35  43";
    String slotB = "13  17  23  27  39  42";
    String slotC = "22  25  31  33  37  42";
    String slotD = "16  22  23  25  31  32";
    String slotE = "11  14  17  18  25  28";

    List<Integer> numbers = new ArrayList<>();
    numbers.addAll(parseNumbers(slotA));
    numbers.addAll(parseNumbers(slotB));
    numbers.addAll(parseNumbers(slotC));
    numbers.addAll(parseNumbers(slotD));
    numbers.addAll(parseNumbers(slotE));

    final int requiredUniqueCount = 12;
    final int maxNumberRange = 45;
    final int lottoSize = 6;

    // 고유한 숫자가 12개 미만일 경우 랜덤 숫자 추가
    ensureUniqueNumbers(numbers, requiredUniqueCount, maxNumberRange);

    // 중복된 것 우선순위 적용
    List<Integer> prioritizedDuplicates = selectUniqueNumbers(numbers, lottoSize, true);
    prioritizedDuplicates.sort(Comparator.naturalOrder());
    System.out.println("중복된 것 우선순위 적용: " + prioritizedDuplicates);

    // 중복되지 않은 것 우선순위 적용
    List<Integer> prioritizedUniques = selectUniqueNumbers(numbers, lottoSize, false);
    prioritizedUniques.sort(Comparator.naturalOrder());
    System.out.println("중복되지 않은 것 우선순위 적용: " + prioritizedUniques);

    // 완전 랜덤 01
    List<Integer> randomSelection = selectRandomNumbers(numbers, lottoSize);
    randomSelection.sort(Comparator.naturalOrder());
    System.out.println("완전 랜덤 01: " + randomSelection);

    // 완전 랜덤 02
    randomSelection = selectRandomNumbers(numbers, lottoSize);
    randomSelection.sort(Comparator.naturalOrder());
    System.out.println("완전 랜덤 02: " + randomSelection);

    // 완전 랜덤 03
    randomSelection = selectRandomNumbers(numbers, lottoSize);
    randomSelection.sort(Comparator.naturalOrder());
    System.out.println("완전 랜덤 03: " + randomSelection);
  }

  public static List<Integer> parseNumbers(String slot) {
    List<Integer> numbers = new ArrayList<>();
    String[] parts = slot.trim().split("\\s+");
    for (String part : parts) {
      numbers.add(Integer.parseInt(part));
    }
    return numbers;
  }

  public static void ensureUniqueNumbers(
      List<Integer> numbers, int requiredUniqueCount, int maxRange) {
    Set<Integer> uniqueNumbers = new HashSet<>(numbers);
    Random random = new Random();

    while (uniqueNumbers.size() < requiredUniqueCount) {
      int randomNum = random.nextInt(maxRange) + 1; // 1부터 maxRange까지의 랜덤 숫자
      uniqueNumbers.add(randomNum);
    }

    numbers.addAll(uniqueNumbers);
  }

  public static List<Integer> selectUniqueNumbers(
      List<Integer> numbers, int count, boolean prioritizeDuplicates) {
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    for (int num : numbers) {
      frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }

    PriorityQueue<Integer> priorityQueue =
        new PriorityQueue<>(
            (a, b) ->
                prioritizeDuplicates
                    ? frequencyMap.get(b) - frequencyMap.get(a)
                    : frequencyMap.get(a) - frequencyMap.get(b));
    priorityQueue.addAll(frequencyMap.keySet());

    Set<Integer> selectedSet = new HashSet<>();
    while (selectedSet.size() < count && !priorityQueue.isEmpty()) {
      int num = priorityQueue.poll();
      selectedSet.add(num);
    }

    List<Integer> result = new ArrayList<>(selectedSet);
    Random random = new Random();

    while (result.size() < count) {
      int randomIndex = random.nextInt(numbers.size());
      int randomNum = numbers.get(randomIndex);
      if (!selectedSet.contains(randomNum)) {
        result.add(randomNum);
        selectedSet.add(randomNum);
      }
    }

    return result;
  }

  public static List<Integer> selectRandomNumbers(List<Integer> numbers, int count) {
    List<Integer> result = new ArrayList<>();
    Random random = new Random();
    Set<Integer> selectedSet = new HashSet<>();

    while (result.size() < count) {
      int randomIndex = random.nextInt(numbers.size());
      int randomNum = numbers.get(randomIndex);
      if (!selectedSet.contains(randomNum)) {
        result.add(randomNum);
        selectedSet.add(randomNum);
      }
    }

    return result;
  }
}
