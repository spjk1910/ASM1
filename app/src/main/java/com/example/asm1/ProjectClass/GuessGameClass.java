package com.example.asm1.ProjectClass;

import java.util.Random;

public class GuessGameClass {
    private QuestionClass[] englishQuestions;
    private QuestionClass[] vietnameseQuestions;
    private QuestionClass currentQuestion;

    public GuessGameClass() {
        englishQuestions = new QuestionClass[]{
                new QuestionClass("(Bot) Which algorithm finds the shortest path between nodes in a graph, especially useful for weighted graphs?",
                        "Dijkstra", new String[]{"(Bot) Hint 1: This algorithm operates on graphs with non-negative edge weights",
                        "(Bot) Hint 2: It starts from the source node and explores all possible paths to find the shortest one",
                        "(Bot) Hint 3: It uses a greedy approach, always selecting the closest node to the source"}),

                new QuestionClass("(Bot) Which sorting algorithm divides the array into two halves, recursively sorts them, and then merges the sorted halves?",
                        "Merge Sort", new String[]{"(Bot) Hint 1: This algorithm follows the divide-and-conquer paradigm",
                        "(Bot) Hint 2: It has a worst-case time complexity of O(n log n)",
                        "(Bot) Hint 3: It is often used in situations where stability is important, such as sorting linked lists"}),

                new QuestionClass("(Bot) Which algorithm efficiently finds the position of a target value within a sorted array by repeatedly dividing the search interval in half?",
                        "Binary Search", new String[]{"(Bot) Hint 1: It compares the target with the middle element of the array",
                        "(Bot) Hint 2: The algorithm eliminates half of the remaining search space after each comparison",
                        "(Bot) Hint 3: It has a time complexity of O(log n)"}),

                new QuestionClass("(Bot) Which sorting algorithm repeatedly selects the minimum element from the unsorted portion of the array and swaps it with the first unsorted element?",
                        "Selection Sort", new String[]{"(Bot) Hint 1: This algorithm has a time complexity of O(n²) in all cases",
                        "(Bot) Hint 2: It is an in-place comparison-based sorting algorithm",
                        "(Bot) Hint 3: Unlike Bubble Sort, it doesn’t repeatedly swap adjacent elements"}),

                new QuestionClass("(Bot) Which graph traversal algorithm explores as far as possible along each branch before backtracking? (Write full name)",
                        "Depth First Search", new String[]{"(Bot) Hint 1: It uses a stack (or recursion) to keep track of the vertices to visit",
                        "(Bot) Hint 2: It is useful for pathfinding, topological sorting, and cycle detection",
                        "(Bot) Hint 3: The algorithm explores one vertex and its descendants before moving on to the next"}),

                new QuestionClass("(Bot) Which sorting algorithm builds the sorted array one element at a time by inserting elements into their correct position in the already sorted part of the array?",
                        "Insertion Sort", new String[]{"(Bot) Hint 1: This algorithm works similarly to how you might sort playing cards in your hands",
                        "(Bot) Hint 2: It is more efficient for small datasets or nearly sorted arrays",
                        "(Bot) Hint 3: It has a worst-case time complexity of O(n²)"}),

                new QuestionClass("(Bot) Which algorithm finds the minimum spanning tree of a graph by sorting all edges in increasing order of weight and adding edges without forming a cycle?",
                        "Kruskal", new String[]{"(Bot) Hint 1: The algorithm uses a disjoint-set data structure to detect cycles",
                        "(Bot) Hint 2: It is a greedy algorithm that always selects the next smallest edge",
                        "(Bot) Hint 3: This algorithm is efficient when the graph is sparse"}),

                new QuestionClass("(Bot) Which sorting algorithm uses a binary heap data structure to build a max-heap or min-heap and then sorts the array?",
                        "Heap Sort", new String[]{"(Bot) Hint 1: It has an O(n log n) time complexity in both the best and worst cases",
                        "(Bot) Hint 2: This algorithm is in-place, meaning it doesn’t require extra space for a new array",
                        "(Bot) Hint 3: It is efficient for applications where data is being inserted and removed frequently"}),

                new QuestionClass("(Bot) Which problem involves finding a path in a graph that visits every vertex exactly once?",
                        "Hamiltonian Path", new String[]{"(Bot) Hint 1: This problem is NP-complete, meaning no known polynomial-time solution exists",
                        "(Bot) Hint 2: It is a special case of the traveling salesman problem",
                        "(Bot) Hint 3: The algorithm explores all possible paths and backtracks when it encounters a dead-end"}),

                new QuestionClass("(Bot) Which sorting algorithm repeatedly compares adjacent elements and swaps them if they are in the wrong order?",
                        "Bubble Sort", new String[]{"(Bot) Hint 1: Despite its simplicity, it is inefficient for large datasets",
                        "(Bot) Hint 2: It has a worst-case time complexity of O(n²)",
                        "(Bot) Hint 3: This algorithm gets its name from the way smaller elements to the top of the list"}),

                new QuestionClass("(Bot) Which algorithm is used to find the minimum spanning tree of a graph by starting from an arbitrary vertex and growing the tree by adding edges with the smallest weight?",
                        "Prim", new String[]{"(Bot) Hint 1: This algorithm is similar to Dijkstra’s algorithm but focuses on connecting all vertices",
                        "(Bot) Hint 2: It uses a priority queue to always add the smallest edge to the tree",
                        "(Bot) Hint 3: The algorithm works best with dense graphs"}),

                new QuestionClass("(Bot) Which sorting algorithm selects a pivot element and partitions the array into two sub-arrays, sorting each recursively?",
                        "Quick Sort", new String[]{"(Bot) Hint 1: This algorithm is a divide-and-conquer technique",
                        "(Bot) Hint 2: The pivot element divides the array into two parts: elements less than the pivot and elements greater than the pivot",
                        "(Bot) Hint 3: Its average time complexity is O(n log n), but it can degrade to O(n²) in the worst case"}),

                new QuestionClass("(Bot) Which algorithm is used to find strongly connected components in a directed graph?",
                        "Tarjan", new String[]{"(Bot) Hint 1: This algorithm involves keeping track of discovery times and low-link values to efficiently detect SCCs",
                        "(Bot) Hint 2: It uses Depth First Search (DFS) to identify the components",
                        "(Bot) Hint 3: The time complexity of this algorithm is O(V + E), where V is the number of vertices and E is the number of edges"}),

                new QuestionClass("(Bot) Which algorithm is used to solve a Sudoku puzzle by filling the board such that each row, column, and subgrid contain all the digits from 1 to 9?",
                        "Backtracking", new String[]{"(Bot) Hint 1: The solution involves recursive calls to test different digit placements",
                        "(Bot) Hint 2: It uses constraint propagation to reduce the search space",
                        "(Bot) Hint 3: This algorithm systematically tries filling empty cells with digits and backtracks when it encounters an invalid configuration"}),

                new QuestionClass("(Bot) Which OOP concept allows a new class to inherit properties and behaviors (methods) from an existing class?",
                        "Inheritance", new String[]{"(Bot) Hint 1: This concept promotes code reusability by enabling a class to derive from another class",
                        "(Bot) Hint 2: The new class, called the subclass, can override methods of the parent class",
                        "(Bot) Hint 3: It establishes an Is-a relationship between the subclass and the superclass"})};

        vietnameseQuestions = new QuestionClass[]{
                new QuestionClass("(Bot) Thuật toán nào tìm đường đi ngắn nhất giữa các nút trong đồ thị, đặc biệt hữu ích cho các đồ thị có trọng số?",
                        "Dijkstra", new String[]{"(Bot) Gợi ý 1: Thuật toán này hoạt động trên các đồ thị có trọng số cạnh không âm",
                        "(Bot) Gợi ý 2: Nó bắt đầu từ nút nguồn và khám phá tất cả các đường đi có thể để tìm đường ngắn nhất",
                        "(Bot) Gợi ý 3: Nó sử dụng phương pháp tham lam, luôn chọn nút gần nhất với nguồn"}),

                new QuestionClass("(Bot) Thuật toán sắp xếp nào chia mảng thành hai phần, sắp xếp đệ quy chúng, rồi gộp các phần đã sắp xếp lại?",
                        "Merge Sort", new String[]{"(Bot) Gợi ý 1: Thuật toán này theo phương pháp chia và trị",
                        "(Bot) Gợi ý 2: Nó có độ phức tạp thời gian trong trường hợp tồi tệ là O(n log n)",
                        "(Bot) Gợi ý 3: Nó thường được sử dụng trong các tình huống mà tính ổn định quan trọng, chẳng hạn như sắp xếp danh sách liên kết"}),

                new QuestionClass("(Bot) Thuật toán nào tìm vị trí của giá trị mục tiêu trong mảng đã được sắp xếp bằng cách chia khoảng tìm kiếm thành hai nửa?",
                        "Binary Search", new String[]{"(Bot) Gợi ý 1: Nó so sánh mục tiêu với phần tử giữa của mảng",
                        "(Bot) Gợi ý 2: Thuật toán loại bỏ một nửa không gian tìm kiếm còn lại sau mỗi lần so sánh",
                        "(Bot) Gợi ý 3: Nó có độ phức tạp thời gian là O(log n)"}),

                new QuestionClass("(Bot) Thuật toán sắp xếp nào chọn phần tử nhỏ nhất từ phần chưa sắp xếp của mảng và hoán đổi nó với phần tử chưa sắp xếp đầu tiên?",
                        "Selection Sort", new String[]{"(Bot) Gợi ý 1: Thuật toán này có độ phức tạp thời gian là O(n²) trong mọi trường hợp",
                        "(Bot) Gợi ý 2: Nó là thuật toán sắp xếp so sánh theo kiểu tại chỗ",
                        "(Bot) Gợi ý 3: Không giống như Bubble Sort, nó không hoán đổi các phần tử liền kề nhiều lần"}),

                new QuestionClass("(Bot) Thuật toán duyệt đồ thị nào khám phá càng xa càng tốt dọc theo mỗi nhánh trước khi quay lại? (Viết tên đầy đủ)",
                        "Depth First Search", new String[]{"(Bot) Gợi ý 1: Nó sử dụng ngăn xếp (hoặc đệ quy) để theo dõi các đỉnh cần duyệt",
                        "(Bot) Gợi ý 2: Nó hữu ích cho tìm đường, sắp xếp topo và phát hiện chu trình",
                        "(Bot) Gợi ý 3: Thuật toán khám phá một đỉnh và các đỉnh con của nó trước khi chuyển sang đỉnh tiếp theo"}),

                new QuestionClass("(Bot) Thuật toán sắp xếp nào xây dựng mảng đã sắp xếp một phần tử một lần bằng cách chèn các phần tử vào vị trí đúng trong phần đã sắp xếp?",
                        "Insertion Sort", new String[]{"(Bot) Gợi ý 1: Thuật toán này hoạt động giống như cách bạn sắp xếp các thẻ bài",
                        "(Bot) Gợi ý 2: Nó hiệu quả hơn đối với các tập dữ liệu nhỏ hoặc mảng đã gần sắp xếp",
                        "(Bot) Gợi ý 3: Nó có độ phức tạp thời gian trong trường hợp tồi tệ là O(n²)"}),

                new QuestionClass("(Bot) Thuật toán nào tìm cây khung nhỏ nhất của đồ thị bằng cách sắp xếp tất cả các cạnh theo thứ tự tăng dần của trọng số và thêm các cạnh mà không tạo thành chu trình?",
                        "Kruskal", new String[]{"(Bot) Gợi ý 1: Thuật toán sử dụng cấu trúc dữ liệu tập hợp phân rã để phát hiện chu trình",
                        "(Bot) Gợi ý 2: Nó là thuật toán tham lam luôn chọn cạnh nhỏ nhất tiếp theo",
                        "(Bot) Gợi ý 3: Thuật toán này hiệu quả khi đồ thị thưa"}),

                new QuestionClass("(Bot) Thuật toán sắp xếp nào sử dụng cấu trúc dữ liệu heap nhị phân để xây dựng heap lớn hoặc nhỏ, sau đó sắp xếp mảng?",
                        "Heap Sort", new String[]{"(Bot) Gợi ý 1: Nó có độ phức tạp thời gian là O(n log n) cả trong trường hợp tốt nhất và tồi tệ nhất",
                        "(Bot) Gợi ý 2: Thuật toán này là thuật toán tại chỗ, nghĩa là không cần không gian bổ sung cho một mảng mới",
                        "(Bot) Gợi ý 3: Nó hiệu quả cho các ứng dụng trong đó dữ liệu được chèn và loại bỏ thường xuyên"}),

                new QuestionClass("(Bot) Vấn đề nào yêu cầu tìm đường đi trong đồ thị thăm tất cả các đỉnh chính xác một lần?",
                        "Hamiltonian Path", new String[]{"(Bot) Gợi ý 1: Vấn đề này là NP-hoàn chỉnh, có nghĩa là không có giải pháp thời gian đa thức nào được biết đến",
                        "(Bot) Gợi ý 2: Nó là một trường hợp đặc biệt của vấn đề người bán hàng du lịch",
                        "(Bot) Gợi ý 3: Thuật toán khám phá tất cả các đường đi và quay lại khi gặp phải một ngõ cụt"}),

                new QuestionClass("(Bot) Thuật toán sắp xếp nào liên tục so sánh các phần tử liền kề và hoán đổi chúng nếu chúng ở thứ tự sai?",
                        "Bubble Sort", new String[]{"(Bot) Gợi ý 1: Mặc dù đơn giản, nhưng nó không hiệu quả cho các tập dữ liệu lớn",
                        "(Bot) Gợi ý 2: Nó có độ phức tạp thời gian trong trường hợp tồi tệ là O(n²)",
                        "(Bot) Gợi ý 3: Thuật toán này có tên gọi từ cách các phần tử nhỏ hơn nổi lên phía trên của danh sách"}),

                new QuestionClass("(Bot) Thuật toán nào được sử dụng để tìm cây khung nhỏ nhất của đồ thị bằng cách bắt đầu từ một đỉnh tùy ý và phát triển cây bằng cách thêm các cạnh có trọng số nhỏ nhất?",
                        "Prim", new String[]{"(Bot) Gợi ý 1: Thuật toán này giống với thuật toán Dijkstra nhưng tập trung vào việc kết nối tất cả các đỉnh",
                        "(Bot) Gợi ý 2: Nó sử dụng hàng đợi ưu tiên để luôn thêm cạnh nhỏ nhất vào cây",
                        "(Bot) Gợi ý 3: Thuật toán này hoạt động tốt nhất với các đồ thị dày đặc"}),

                new QuestionClass("(Bot) Thuật toán sắp xếp nào chọn một phần tử làm pivot và phân tách mảng thành hai mảng con, sắp xếp từng mảng đệ quy?",
                        "Quick Sort", new String[]{"(Bot) Gợi ý 1: Thuật toán này là một kỹ thuật chia và trị",
                        "(Bot) Gợi ý 2: Phần tử pivot phân chia mảng thành hai phần: các phần tử nhỏ hơn pivot và các phần tử lớn hơn pivot",
                        "(Bot) Gợi ý 3: Độ phức tạp thời gian trung bình của nó là O(n log n), nhưng có thể suy giảm thành O(n²) trong trường hợp tồi tệ"}),

                new QuestionClass("(Bot) Thuật toán nào được sử dụng để tìm các thành phần mạnh liên thông trong đồ thị có hướng?",
                        "Tarjan", new String[]{"(Bot) Gợi ý 1: Thuật toán này liên quan đến việc theo dõi thời gian khám phá và giá trị low-link để phát hiện SCC một cách hiệu quả",
                        "(Bot) Gợi ý 2: Nó sử dụng tìm kiếm theo chiều sâu (DFS) để xác định các thành phần",
                        "(Bot) Gợi ý 3: Độ phức tạp thời gian của thuật toán này là O(V + E), trong đó V là số đỉnh và E là số cạnh"}),

                new QuestionClass("(Bot) Thuật toán nào được sử dụng để giải bài toán Sudoku bằng cách điền vào bảng sao cho mỗi hàng, mỗi cột và mỗi ô con chứa tất cả các chữ số từ 1 đến 9?",
                        "Backtracking", new String[]{"(Bot) Gợi ý 1: Giải pháp bao gồm các cuộc gọi đệ quy để thử các vị trí khác nhau của chữ số",
                        "(Bot) Gợi ý 2: Nó sử dụng sự truyền bá ràng buộc để giảm không gian tìm kiếm",
                        "(Bot) Gợi ý 3: Thuật toán này thử điền vào các ô trống với các chữ số và quay lại khi gặp cấu hình không hợp lệ"}),

                new QuestionClass("(Bot) Khái niệm OOP nào cho Gợi ý một lớp mới kế thừa các thuộc tính và hành vi (phương thức) từ một lớp hiện có?",
                        "Inheritance", new String[]{"(Bot) Hint 1: Khái niệm này thúc đẩy tái sử dụng mã bằng cách cho phép một lớp kế thừa từ lớp khác",
                        "(Bot) Gợi ý 2: Lớp mới, gọi là lớp con, có thể ghi đè các phương thức của lớp cha",
                        "(Bot) Gợi ý 3: Nó thiết lập mối quan hệ Is-a giữa lớp con và lớp cha"})};
    }

    public void startGame(String languageCode)
    {
        Random random = new Random();

        if (languageCode == "en")
        {
            currentQuestion = englishQuestions[random.nextInt(englishQuestions.length)];
        } else if (languageCode == "vi")
        {
            currentQuestion = vietnameseQuestions[random.nextInt(vietnameseQuestions.length)];
        }
    }

    public QuestionClass createQuestion() {
        return currentQuestion;
    }
}