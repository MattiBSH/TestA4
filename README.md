# TestA4

Run Jacoco:
mvn clean test

Run PiTest in root of project:
mvn test-compile org.pitest:pitest-maven:mutationCoverage

Play Snake
Run the SnakeGame.java file

Testing Mockito
1. How do you verify that a mock was called?
Using mock.verify()



1. How do you verify that a mock was called?

import static org.mockito.Mockito.*;

List<String> mockList = mock(List.class);
mockList.add("Group 4");
mockList.size();

verify(mockList).add("Group 4"); // verify that this list contains the string "Group 4"
verify(mockList).add(any(String.Class)); //verify that their is any strings inside the list.
 
2. How do you verify that a mock was NOT called? 

import static org.mockito.Mockito.*;;

List<String> mockList = mock(List.class);
mockList.add("Group 4");
mockList.size();

verify(mockList, never()).add("Group 9000"); // verify that the Group 9000 was never in the list or added.
verify(mockList, never()).remove("Group 4"); // verify that Group 4 was never removed.
verify(mockList, never()).clear(); // verify that the list was not cleared.
 
3. How do you specify how many times a mock should have been called?

List<String> mockList = mock(List.class);
mockList.add("Group 4");
mockList.size();
mockedList.clear(); mockedList.clear();

verify(mockList, atLeast(2)).clear(); // verify that the clear function has been called atleast two times.
verify(mockList, atMost(2)).clear(); // verify that the clear function has been called at most two times.
 
4. How do you verify a mock was called with specific arguments?

import static org.mockito.Mockito.*;

List<String> mockList = mock(List.class);
mockList.add("Group 9000");

// like we see under question 2. We can use the `.add("Group 9000");` to check if some function has been called with an argument.
verify(mockList).add("Group 9000"); /

5. How do you use a predicate to verify the properties of the arguments given to a call to the mock?


class G1 {
    boolean bool(boolean something) {
        return something;
    }

    int alwaysZero(int number) {
        return 0;
    }
    
    int foobar(int numb) {
        return numb;
    }
}

G1 mockFoo = mock(G1.class);
when(mockFoo.bool(anyInt(), anyBoolean(), any(Object.class))).thenReturn(true);
