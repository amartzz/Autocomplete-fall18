
/*************************************************************************
 * @author Kevin Wayne
 *
 * Description: A term and its weight.
 * 
 *************************************************************************/

import java.util.Comparator;

public class Term implements Comparable<Term> {

	private final String myWord;
	private final double myWeight;

	/**
	 * The constructor for the Term class. Should set the values of word and
	 * weight to the inputs, and throw the exceptions listed below
	 * 
	 * @param word
	 *            The word this term consists of
	 * @param weight
	 *            The weight of this word in the Autocomplete algorithm
	 * @throws NullPointerException
	 *             if word is null
	 * @throws IllegalArgumentException
	 *             if weight is negative
	 */
	public Term(String word, double weight) {
		// DONE: Complete Term constructor
		myWord = word;
		myWeight = weight;
		if (word.equals(null)) throw new NullPointerException ("world is null");
		if (weight<0) throw new IllegalArgumentException("weight is negative");
	}
	
	/**
	 * The default sorting of Terms is lexicographical ordering.
	 */
	public int compareTo(Term that) {
		return myWord.compareTo(that.myWord);
	}

	/**
	 * Getter methods, use these in other classes which use Term
	 */
	public String getWord() {
		return myWord;
	}

	public double getWeight() {
		return myWeight;
	}

	public String toString() {
		return String.format("(%2.1f,%s)", myWeight, myWord);
	}
	
	@Override
	public boolean equals(Object o) {
		Term other = (Term) o;
		return this.compareTo(other) == 0;
	}

	/**
	 * A Comparator for comparing Terms using a set number of the letters they
	 * start with. This Comparator may be useful in writing your implementations
	 * of Autocompletors.
	 *
	 */
	public static class PrefixOrder implements Comparator<Term> {
		private final int myPrefixSize;

		public PrefixOrder(int r) {
			this.myPrefixSize = r;
		}

		/**
		 * Compares v and w lexicographically using only their first r letters.
		 * If the first r letters are the same, then v and w should be
		 * considered equal. This method should take O(r) to run, and be
		 * independent of the length of v and w's length. You can access the
		 * Strings to compare using v.word and w.word.
		 * 
		 * @param v/w
		 *            - Two Terms whose words are being compared
		 */
		public int compare(Term v, Term w) {
			// DONE: Implement compare, reminder:comparing v and w

			//both words less than prefix size
			if (v.getWord().length() < myPrefixSize && w.getWord().length() < myPrefixSize) {
				//return 0 if v==w
				if(v.equals(w)) return 0;
			}
			
			if(v.getWord().length() < myPrefixSize) {
				for(int i=0; i< v.getWord().length(); i++) {
					if (v.getWord().charAt(i) != w.getWord().charAt(i)) {
						return v.getWord().charAt(i) - w.getWord().charAt(i);
					}
				}
				return -1;
			}
			
			if (w.getWord().length() < myPrefixSize) {
				for (int j=0; j< w.getWord().length(); j++) {
					if (v.getWord().charAt(j) != w.getWord().charAt(j)) {
						return v.getWord().charAt(j) - w.getWord().charAt(j);
					}
				}	
				return 1;
			}
			for(int k=0; k< myPrefixSize; k++) {
				if (v.getWord().charAt(k) != w.getWord().charAt(k)) {
					return v.getWord().charAt(k)- w.getWord().charAt(k);
				}
			}
			//else statement essentially
			return 0;
		}
	
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in descending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class ReverseWeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			// DONE: implement compare, look at weights in descending order
			/**
			 * if <, return 1
			 * if ==, return 0
			 * if > return -1
			 */
			if (v.getWeight() < w.getWeight()) {
				return 1;
			}
			if (v.getWeight() == w.getWeight()) {
				return 0;
			}
			else {
				return -1;
			}
		}
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in ascending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class WeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			// DONE: implement compare
			/**
			 * same idea as above, but in ascending order
			 * if <, return -1
			 * if ==, return 0
			 * if > return 1
			 */
			
			if (v.getWeight() < w.getWeight()) {
				return -1;
			}
			if (v.getWeight() == w.getWeight()) {
				return 0;
			}
			else{
				return 1;
			}
		}
	}
}
