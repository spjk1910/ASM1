package com.example.asm1;

import java.util.Random;

public class GuessGameClass {
    private QuestionClass[] englishQuestions;
    private QuestionClass[] vietnameseQuestions;
    private QuestionClass currentQuestion;

    public GuessGameClass() {
        englishQuestions = new QuestionClass[]{
                new QuestionClass("Which algorithm finds the shortest path between nodes in a graph, especially useful for weighted graphs?",
                        "Dijkstra", new String[]{"This algorithm operates on graphs with non-negative edge weights",
                        "It starts from the source node and explores all possible paths to find the shortest one",
                        "It uses a greedy approach, always selecting the closest node to the source"}),

                new QuestionClass("Which sorting algorithm divides the array into two halves, recursively sorts them, and then merges the sorted halves?",
                        "Merge Sort", new String[]{"This algorithm follows the divide-and-conquer paradigm",
                        "It has a worst-case time complexity of O(n log n)",
                        "It is often used in situations where stability is important, such as sorting linked lists"}),

                new QuestionClass("Which algorithm efficiently finds the position of a target value within a sorted array by repeatedly dividing the search interval in half?",
                        "Binary Search", new String[]{"It compares the target with the middle element of the array",
                        "The algorithm eliminates half of the remaining search space after each comparison",
                        "It has a time complexity of O(log n)"}),

                new QuestionClass("Which sorting algorithm repeatedly selects the minimum element from the unsorted portion of the array and swaps it with the first unsorted element?",
                        "Selection Sort", new String[]{"This algorithm has a time complexity of O(n²) in all cases",
                        "It is an in-place comparison-based sorting algorithm",
                        "Unlike Bubble Sort, it doesn’t repeatedly swap adjacent elements"}),

                new QuestionClass("Which graph traversal algorithm explores as far as possible along each branch before backtracking? (Write full name)",
                        "Depth First Search", new String[]{"It uses a stack (or recursion) to keep track of the vertices to visit",
                        "It is useful for pathfinding, topological sorting, and cycle detection",
                        "The algorithm explores one vertex and its descendants before moving on to the next"}),

                new QuestionClass("Which sorting algorithm builds the sorted array one element at a time by inserting elements into their correct position in the already sorted part of the array?",
                        "Insertion Sort", new String[]{"This algorithm works similarly to how you might sort playing cards in your hands",
                        "It is more efficient for small datasets or nearly sorted arrays",
                        "It has a worst-case time complexity of O(n²)"}),

                new QuestionClass(" Which algorithm finds the minimum spanning tree of a graph by sorting all edges in increasing order of weight and adding edges without forming a cycle?",
                        "Kruskal", new String[]{"The algorithm uses a disjoint-set data structure to detect cycles",
                        "It is a greedy algorithm that always selects the next smallest edge",
                        "This algorithm is efficient when the graph is sparse"}),

                new QuestionClass("Which sorting algorithm uses a binary heap data structure to build a max-heap or min-heap and then sorts the array?",
                        "Heap Sort", new String[]{"It has an O(n log n) time complexity in both the best and worst cases",
                        "This algorithm is in-place, meaning it doesn’t require extra space for a new array",
                        "It is efficient for applications where data is being inserted and removed frequently"}),

                new QuestionClass("Which problem involves finding a path in a graph that visits every vertex exactly once?",
                        "Hamiltonian Path", new String[]{"This problem is NP-complete, meaning no known polynomial-time solution exists",
                        "It is a special case of the traveling salesman problem",
                        "The algorithm explores all possible paths and backtracks when it encounters a dead-end"}),

                new QuestionClass("Which sorting algorithm repeatedly compares adjacent elements and swaps them if they are in the wrong order?",
                        "Bubble Sort", new String[]{" Despite its simplicity, it is inefficient for large datasets",
                        "It has a worst-case time complexity of O(n²)",
                        "This algorithm gets its name from the way smaller elements to the top of the list"}),

                new QuestionClass("Which algorithm is used to find the minimum spanning tree of a graph by starting from an arbitrary vertex and growing the tree by adding edges with the smallest weight?",
                        "Prim", new String[]{"This algorithm is similar to Dijkstra’s algorithm but focuses on connecting all vertices",
                        "It uses a priority queue to always add the smallest edge to the tree",
                        "The algorithm works best with dense graphs"}),

                new QuestionClass("Which sorting algorithm selects a pivot element and partitions the array into two sub-arrays, sorting each recursively?",
                        "Quick Sort", new String[]{"This algorithm is a divide-and-conquer technique",
                        "The pivot element divides the array into two parts: elements less than the pivot and elements greater than the pivot",
                        "Its average time complexity is O(n log n), but it can degrade to O(n²) in the worst case"}),

                new QuestionClass("Which algorithm is used to find strongly connected components in a directed graph?",
                        "Tarjan", new String[]{"This algorithm involves keeping track of discovery times and low-link values to efficiently detect SCCs",
                        "It uses Depth First Search (DFS) to identify the components",
                        "The time complexity of this algorithm is O(V + E), where V is the number of vertices and E is the number of edges"}),

                new QuestionClass("Which algorithm is used to solve a Sudoku puzzle by filling the board such that each row, column, and subgrid contain all the digits from 1 to 9?",
                        "Backtracking", new String[]{"The solution involves recursive calls to test different digit placements",
                        "It uses constraint propagation to reduce the search space",
                        "This algorithm systematically tries filling empty cells with digits and backtracks when it encounters an invalid configuration"}),

                new QuestionClass("Which OOP concept allows a new class to inherit properties and behaviors (methods) from an existing class?",
                        "Inheritance", new String[]{"This concept promotes code reusability by enabling a class to derive from another class",
                        "The new class, called the subclass, can override methods of the parent class",
                        "It establishes an Is-a relationship between the subclass and the superclass"})};

        vietnameseQuestions = new QuestionClass[]{
                new QuestionClass("Thuật toán nào tìm đường đi ngắn nhất giữa các nút trong đồ thị, đặc biệt hữu ích cho các đồ thị có trọng số?",
                        "Dijkstra", new String[]{"Thuật toán này hoạt động trên các đồ thị có trọng số cạnh không âm",
                        "Nó bắt đầu từ nút nguồn và khám phá tất cả các đường đi có thể để tìm đường ngắn nhất",
                        "Nó sử dụng phương pháp tham lam, luôn chọn nút gần nhất với nguồn"}),

                new QuestionClass("Thuật toán sắp xếp nào chia mảng thành hai phần, sắp xếp đệ quy chúng, rồi gộp các phần đã sắp xếp lại?",
                        "Merge Sort", new String[]{"Thuật toán này theo phương pháp chia và trị",
                        "Nó có độ phức tạp thời gian trong trường hợp tồi tệ là O(n log n)",
                        "Nó thường được sử dụng trong các tình huống mà tính ổn định quan trọng, chẳng hạn như sắp xếp danh sách liên kết"}),

                new QuestionClass("Thuật toán nào tìm vị trí của giá trị mục tiêu trong mảng đã được sắp xếp bằng cách chia khoảng tìm kiếm thành hai nửa?",
                        "Binary Search", new String[]{"Nó so sánh mục tiêu với phần tử giữa của mảng",
                        "Thuật toán loại bỏ một nửa không gian tìm kiếm còn lại sau mỗi lần so sánh",
                        "Nó có độ phức tạp thời gian là O(log n)"}),

                new QuestionClass("Thuật toán sắp xếp nào chọn phần tử nhỏ nhất từ phần chưa sắp xếp của mảng và hoán đổi nó với phần tử chưa sắp xếp đầu tiên?",
                        "Selection Sort", new String[]{"Thuật toán này có độ phức tạp thời gian là O(n²) trong mọi trường hợp",
                        "Nó là thuật toán sắp xếp so sánh theo kiểu tại chỗ",
                        "Không giống như Bubble Sort, nó không hoán đổi các phần tử liền kề nhiều lần"}),

                new QuestionClass("Thuật toán duyệt đồ thị nào khám phá càng xa càng tốt dọc theo mỗi nhánh trước khi quay lại? (Viết tên đầy đủ)",
                        "Depth First Search", new String[]{"Nó sử dụng ngăn xếp (hoặc đệ quy) để theo dõi các đỉnh cần duyệt",
                        "Nó hữu ích cho tìm đường, sắp xếp topo và phát hiện chu trình",
                        "Thuật toán khám phá một đỉnh và các đỉnh con của nó trước khi chuyển sang đỉnh tiếp theo"}),

                new QuestionClass("Thuật toán sắp xếp nào xây dựng mảng đã sắp xếp một phần tử một lần bằng cách chèn các phần tử vào vị trí đúng trong phần đã sắp xếp?",
                        "Insertion Sort", new String[]{"Thuật toán này hoạt động giống như cách bạn sắp xếp bài tay bài bài bài thẻ bài",
                        "Nó hiệu quả hơn đối với các tập dữ liệu nhỏ hoặc mảng đã gần sắp xếp",
                        "Nó có độ phức tạp thời gian trong trường hợp tồi tệ là O(n²)"}),

                new QuestionClass("Thuật toán nào tìm cây khung nhỏ nhất của đồ thị bằng cách sắp xếp tất cả các cạnh theo thứ tự tăng dần của trọng số và thêm các cạnh mà không tạo thành chu trình?",
                        "Kruskal", new String[]{"Thuật toán sử dụng cấu trúc dữ liệu tập hợp phân rã để phát hiện chu trình",
                        "Nó là thuật toán tham lam luôn chọn cạnh nhỏ nhất tiếp theo",
                        "Thuật toán này hiệu quả khi đồ thị thưa"}),

                new QuestionClass("Thuật toán sắp xếp nào sử dụng cấu trúc dữ liệu heap nhị phân để xây dựng heap lớn hoặc nhỏ, sau đó sắp xếp mảng?",
                        "Heap Sort", new String[]{"Nó có độ phức tạp thời gian là O(n log n) cả trong trường hợp tốt nhất và tồi tệ nhất",
                        "Thuật toán này là thuật toán tại chỗ, nghĩa là không cần không gian bổ sung cho một mảng mới",
                        "Nó hiệu quả cho các ứng dụng trong đó dữ liệu được chèn và loại bỏ thường xuyên"}),

                new QuestionClass("Vấn đề nào yêu cầu tìm đường đi trong đồ thị thăm tất cả các đỉnh chính xác một lần?",
                        "Hamiltonian Path", new String[]{"Vấn đề này là NP-hoàn chỉnh, có nghĩa là không có giải pháp thời gian đa thức nào được biết đến",
                        "Nó là một trường hợp đặc biệt của vấn đề người bán hàng du lịch",
                        "Thuật toán khám phá tất cả các đường đi và quay lại khi gặp phải một ngõ cụt"}),

                new QuestionClass("Thuật toán sắp xếp nào liên tục so sánh các phần tử liền kề và hoán đổi chúng nếu chúng ở thứ tự sai?",
                        "Bubble Sort", new String[]{"Mặc dù đơn giản, nhưng nó không hiệu quả cho các tập dữ liệu lớn",
                        "Nó có độ phức tạp thời gian trong trường hợp tồi tệ là O(n²)",
                        "Thuật toán này có tên gọi từ cách các phần tử nhỏ hơn nổi lên phía trên của danh sách"}),

                new QuestionClass("Thuật toán nào được sử dụng để tìm cây khung nhỏ nhất của đồ thị bằng cách bắt đầu từ một đỉnh tùy ý và phát triển cây bằng cách thêm các cạnh có trọng số nhỏ nhất?",
                        "Prim", new String[]{"Thuật toán này giống với thuật toán Dijkstra nhưng tập trung vào việc kết nối tất cả các đỉnh",
                        "Nó sử dụng hàng đợi ưu tiên để luôn thêm cạnh nhỏ nhất vào cây",
                        "Thuật toán này hoạt động tốt nhất với các đồ thị dày đặc"}),

                new QuestionClass("Thuật toán sắp xếp nào chọn một phần tử làm pivot và phân tách mảng thành hai mảng con, sắp xếp từng mảng đệ quy?",
                        "Quick Sort", new String[]{"Thuật toán này là một kỹ thuật chia và trị",
                        "Phần tử pivot phân chia mảng thành hai phần: các phần tử nhỏ hơn pivot và các phần tử lớn hơn pivot",
                        "Độ phức tạp thời gian trung bình của nó là O(n log n), nhưng có thể suy giảm thành O(n²) trong trường hợp tồi tệ"}),

                new QuestionClass("Thuật toán nào được sử dụng để tìm các thành phần mạnh liên thông trong đồ thị có hướng?",
                        "Tarjan", new String[]{"Thuật toán này liên quan đến việc theo dõi thời gian khám phá và giá trị low-link để phát hiện SCC một cách hiệu quả",
                        "Nó sử dụng tìm kiếm theo chiều sâu (DFS) để xác định các thành phần",
                        "Độ phức tạp thời gian của thuật toán này là O(V + E), trong đó V là số đỉnh và E là số cạnh"}),

                new QuestionClass("Thuật toán nào được sử dụng để giải bài toán Sudoku bằng cách điền vào bảng sao cho mỗi hàng, mỗi cột và mỗi ô con chứa tất cả các chữ số từ 1 đến 9?",
                        "Backtracking", new String[]{"Giải pháp bao gồm các cuộc gọi đệ quy để thử các vị trí khác nhau của chữ số",
                        "Nó sử dụng sự truyền bá ràng buộc để giảm không gian tìm kiếm",
                        "Thuật toán này thử điền vào các ô trống với các chữ số và quay lại khi gặp cấu hình không hợp lệ"}),

                new QuestionClass("Khái niệm OOP nào cho phép một lớp mới kế thừa các thuộc tính và hành vi (phương thức) từ một lớp hiện có?",
                        "Inheritance", new String[]{"Khái niệm này thúc đẩy tái sử dụng mã bằng cách cho phép một lớp kế thừa từ lớp khác",
                        "Lớp mới, gọi là lớp con, có thể ghi đè các phương thức của lớp cha",
                        "Nó thiết lập mối quan hệ Is-a giữa lớp con và lớp cha"})};
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

    public QuestionClass getQuestion() {
        return currentQuestion;
    }
}