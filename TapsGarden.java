/* There is a one-dimensional garden on the x-axis... The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n)... There are n + 1 taps located at points [0, 1, ..., n] in the garden... Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open... Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1...
 * Eg 1:     n = 5      ranges = [3,4,1,1,0,0]                                      Output: 1
 * Eg 2:     n = 3      ranges = [0,0,0,1]                                          Output = -1
 */
import java.util.*;
public class TapsGarden
{
    public int MinimumOpenedTaps(int n, int ranges[])
    {
        int[] dp = new int[n + 1];    // Creating a DP Array...
        for(int i = 0; i < ranges.length; i++) {
            if(ranges[i] == 0) continue;    // If the range of the tap is 0...
            int left = Math.max(0, i - ranges[i]);      // Getting the index of tap...
            dp[left] = Math.max(dp[left], i + ranges[i]);   // Updating the maximum range possible of the current tap...
        }
        int end = 0, farCanReach = 0, cnt = 0;        
        for(int i = 0; i < dp.length && end < n; end = farCanReach) {
            cnt++;    // Updating the counter...
            while(i < dp.length && i <= end) {
                farCanReach = Math.max(farCanReach, dp[i++]);    // Getting the maximum distance which one can cover...                            
            }
            if(end == farCanReach) return -1;    // If some space lefts unoccupied... 
        }
        return cnt;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x;
        System.out.print("Enter the range of the garden : ");
        x = sc.nextInt();
        int taps[] = new int[x+1];
        for(int i = 0; i < taps.length; i++)
        {
            System.out.print("Enter Watering range of "+(i+1)+" th Tap : ");
            taps[i] = sc.nextInt();
        }
        TapsGarden  tapsgarden = new TapsGarden();    // Object creation...
        System.out.println("The Minimum number of Taps to Open : "+tapsgarden.MinimumOpenedTaps(x, taps));
        sc.close();
    }
}

// Time Complexity  - O(n) time...
// Space Complexity - O(n) space...

/* DEDUCTIONS :- 
 * 1. Since we want the maximum distance we can cover, we use the DP Array to maintain the maximum distance possible to traverse from the current tap...
 * 2. When the maximum distance is less than the ending point we move to the next tap to check and increase the counter by one...
 * 3. If any place is left unoccupied we return negative value as the answer...
*/