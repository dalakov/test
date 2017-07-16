package pack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.Logger;

import static org.mockito.Mockito.*;

class SolutionTest {
    @Mock
    Logger logger;
    @InjectMocks
    Solution solution;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMain() {
        Solution.main(new String[]{"arg"});
    }

    @Test
    @DisplayName("testmin")
    void min() {
        int a = 7;
        int b = 8;
        assertEquals(a,Solution.min(a,b));
        a = -8;
        b = -7;
        assertEquals(a,Solution.min(a,b));
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme