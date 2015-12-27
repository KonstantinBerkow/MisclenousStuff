package me.konstantinberkow;

import java.util.*;

public class Main {

    private static final int[] FREQUENCY = new int['Я' + 1];

    private static final Pair[] RUS_FREQUENCY = {
            new Pair(' ', .175F),
            new Pair('О', .090F),
            new Pair('Е', .072F),
            new Pair('А', .062F),
            new Pair('И', .062F),
            new Pair('Т', .053F),
            new Pair('Н', .053F),
            new Pair('С', .045F),
            new Pair('Р', .040F),
            new Pair('В', .038F),
            new Pair('Л', .035F),
            new Pair('К', .028F),
            new Pair('М', .026F),
            new Pair('Д', .025F),
            new Pair('П', .023F),
            new Pair('У', .021F),
            new Pair('Я', .018F),
            new Pair('Ы', .016F),
            new Pair('З', .016F),
            new Pair('Ь', .014F),
            new Pair('Б', .014F),
            new Pair('Г', .013F),
            new Pair('Ч', .012F),
            new Pair('Й', .010F),
            new Pair('Х', .009F),
            new Pair('Ж', .007F),
            new Pair('Ю', .006F),
            new Pair('Ш', .006F),
            new Pair('Ц', .004F),
            new Pair('Щ', .003F),
            new Pair('Э', .003F),
            new Pair('Ф', .002F),
    };

    private static final char[] CHANGES = {
            'Д', ' ',
            'П', 'Е',
//            'Ь', 'Н',
//            'К', 'С',
    };


    public static void main(String[] args) {
        final String secretString = "ГПЬНЬДГБЕКБПДГПЬБДЦДТНТДЩКНЪЦЬБДЕПВТБКЭВОЙБДЙБДХЭВЬ_ДБДОБХДИОБДБЙБДХБЫПОДЕЭО_ДЯНХПИПЙБДГКЖЛЦХЦДЦДБГБЕКПЙБДВДЩБЗЪНЬБЮДЛКПЬНДГЖАЖ";
        final int secretLength = secretString.length();

        SchenksInd.ind(28, 2, 37);
        SchenksInd.ind(7531, 6, 8101);

//        for (int i = 0; i < secretLength; i++) {
//            FREQUENCY[secretString.charAt(i)]++;
//        }
//
//        final Set<Pair> pairs = new TreeSet<>();
//
//        final int length = FREQUENCY.length;
//        for (int i = 0; i < length; i++) {
//            if (FREQUENCY[i] == 0) continue;
//
//            pairs.add(new Pair((char) i, (float) FREQUENCY[i] / secretLength));
//        }
//
//        for (Pair pair : pairs) {
//            System.out.println(pair);
//        }
//        System.out.println("Original:\t" + secretString + '\n');
//
//        final String filtered = filter(secretString.toCharArray(), CHANGES);
//        System.out.println("Filtered:\t" + filtered);
//
//        for (Pair pair : pairs) {
//            final DistanceComparator distanceComparator = new DistanceComparator(pair);
//            final Pair[] sortedPairs = Arrays.copyOf(RUS_FREQUENCY, RUS_FREQUENCY.length);
//            Arrays.sort(sortedPairs, distanceComparator);
//            System.out.println(pair + ": " + Arrays.toString(sortedPairs));
//        }

//        final String changed = alter(secretString.toCharArray(), CHANGES);
//        System.out.println("Changed:\t" + changed);

//        final Map<String, Integer> bigramms = countBigramms(secretString.replace('Д', ' '));
//        for (Map.Entry<String, Integer> entry : bigramms.entrySet()) {
//            System.out.println(entry);
//        }
    }

    private static String filter(final char[] origin, final char[] changes) {
        final int originLength = origin.length;
        final int changesLength = changes.length;
        for (int i = 0; i < originLength; i++) {
            final char currentChar = origin[i];
            boolean used = false;
            for (int j = 0; j < changesLength && !used; j += 2) {
                if (currentChar == changes[j]) {
                    origin[i] = changes[j + 1];
                    used = true;
                }
            }
            if (!used) origin[i] = '-';
        }

        return new String(origin);
    }

    private static String alter(final char[] origin, final char[] changes) {
        final int originLength = origin.length;
        final int changesLength = changes.length;
        for (int i = 0; i < originLength; i++) {
            final char currentChar = origin[i];
            for (int j = 0; j < changesLength; j += 2) {
                if (currentChar == changes[j]) {
                    origin[i] = changes[j + 1];
                    break;
                }
            }
        }

        return new String(origin);
    }

    public static int ind(final int x, final int g, final int p) {
        final int m = findM(p);
        System.out.println("m: " + m);
        final Map<Integer, Integer> mValues = new HashMap<>(m);

        int gm = 1;
        for (int i = 0; i < m; i++) {
            gm = (gm * g) % p;
        }

        System.out.println("g^m: " + gm);

        int current = gm;
        mValues.put(gm, 1);
        for (int i = 2; i <= m; i++) {
            current = (current * gm) % p;
            mValues.put(current, i);
        }

        System.out.println(mValues);

        current = x;
        int answer = -1;
        for (int j = 0; j < m; j++) {
            final Integer index = mValues.get(current);
            if (index != null) {
                answer = index * m - j;
                if (answer < p) return answer;
                answer = -1;
            }
            current = (current * g) % p;
        }

        return answer;
    }

    private static int findM(final int p) {
        return (int) Math.sqrt(p) + 1;
    }

    public static Map<String, Integer> countBigramms(final String string) {
        final Map<String, Integer> result = new TreeMap<>();

        //Split the text into words
        final StringTokenizer tokenizer = new StringTokenizer(string);

        //for each word
        while (tokenizer.hasMoreTokens()) {
            final String currentWord = tokenizer.nextToken();
            String currentBigram;

            for (int i = 0; i < currentWord.length(); i += 2) {
                //if there are at least 2 characters in the current word
                if (currentWord.length() >= (i + 2)) {
                    //separate 2-letter bigram
                    currentBigram = currentWord.substring(i, i + 2);
                } else { //only 1 character left in current word
                    //separate 1-letter bigram
                    currentBigram = currentWord.substring(i);
                }

                final Integer count = result.get(currentBigram);
                result.put(currentBigram, count == null ? 1 : count + 1);
            }
        }

        return result;
    }
}