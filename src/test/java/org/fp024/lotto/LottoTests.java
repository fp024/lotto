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
 1152회 도전 👍
 중복된 것 우선순위 적용: [15, 24, 29, 32, 35, 39]
 중복되지 않은 것 우선순위 적용: [2, 35, 39, 43, 44, 45]
 완전 랜덤 01: [2, 10, 29, 31, 32, 45]
 완전 랜덤 02: [9, 10, 16, 20, 34, 39]
 완전 랜덤 03: [11, 15, 17, 21, 22, 32]
*/
class LottoTests {
  @Test
  void run() {
    String slotA = "2  15  29  32  33  45";
    String slotB = "4  9  24  32  39  44";
    String slotC = "7  10  21  23  34  43";
    String slotD = "3  11  17  22  24  35";
    String slotE = "15  16  20  28  29  31";

    List<Integer> numbers = new ArrayList<>();
    numbers.addAll(parseNumbers(slotA));
    numbers.addAll(parseNumbers(slotB));
    numbers.addAll(parseNumbers(slotC));
    numbers.addAll(parseNumbers(slotD));
    numbers.addAll(parseNumbers(slotE));

    // 고유한 숫자가 12개 미만일 경우 랜덤 숫자 추가
    ensureUniqueNumbers(numbers, 12, 45);

    // 중복된 것 우선순위 적용
    List<Integer> prioritizedDuplicates = selectUniqueNumbers(numbers, 6, true);
    prioritizedDuplicates.sort(Comparator.naturalOrder());
    System.out.println("중복된 것 우선순위 적용: " + prioritizedDuplicates);

    // 중복되지 않은 것 우선순위 적용
    List<Integer> prioritizedUniques = selectUniqueNumbers(numbers, 6, false);
    prioritizedUniques.sort(Comparator.naturalOrder());
    System.out.println("중복되지 않은 것 우선순위 적용: " + prioritizedUniques);

    // 완전 랜덤 01
    List<Integer> randomSelection = selectRandomNumbers(numbers, 6);
    randomSelection.sort(Comparator.naturalOrder());
    System.out.println("완전 랜덤 01: " + randomSelection);

    // 완전 랜덤 02
    randomSelection = selectRandomNumbers(numbers, 6);
    randomSelection.sort(Comparator.naturalOrder());
    System.out.println("완전 랜덤 02: " + randomSelection);

    // 완전 랜덤 03
    randomSelection = selectRandomNumbers(numbers, 6);
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
