package tlu.cse.android.ht63.quanlychitieu.ui.Chatbot;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.BTL_Quanlychitieu.Adapter.MessageAdapter;
import com.google.BTL_Quanlychitieu.Model.Message;
import com.google.BTL_Quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends Fragment {

    RecyclerView recyclerView;
    TextView welcomeTextView;
    EditText messageEditText;
    ImageButton sendButton;
    List<Message> messageList;

    MessageAdapter messageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Initialize views
        recyclerView = view.findViewById(R.id.recycler_view);
        welcomeTextView = view.findViewById(R.id.welcome_text);
        messageEditText = view.findViewById(R.id.message_edit_text);
        sendButton = view.findViewById(R.id.send_btn);
        messageList = new ArrayList<>();

        //setup recycler view
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(requireContext());
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        // Add click listener for send button
        sendButton.setOnClickListener(v -> {
            String question = messageEditText.getText().toString().trim();
            if (!question.isEmpty()) {
                // Gửi tin nhắn của người dùng ngay lập tức
                Toast.makeText(requireContext(), question, Toast.LENGTH_LONG);
                addToChat(question, Message.SENT_BY_ME); // Gửi tin nhắn của người dùng
                messageEditText.setText("");
                welcomeTextView.setVisibility(View.GONE);

                // Dùng Handler để tạo độ trễ trước khi bot trả lời
                new Handler().postDelayed(() -> {
                    // Trả lời bot với câu trả lời khác nhau dựa trên câu hỏi của người dùng
                    String response = "";

                    if (question.contains("xin chào") || question.contains("xin chao")) {
                        response = "Hôm nay bạn đã tiêu những gì hay khai mau";
                    } else if (question.contains("2") || question.contains("gì")) {
                        response = "Bạn có thể hỏi tôi bất kỳ câu hỏi nào!";
                    } else if (question.contains("3") || question.contains("Tôi nên đi du lịch hay mua vàng khi có 10 triệu")) {
                        response = "Mua vàng";
                    } else if (question.contains("giới thiệu")) {
                        response = "Tôi là chatbot, có thể giúp bạn giải đáp các câu hỏi về dịch vụ của chúng tôi!";
                    } else {
                        response = "Xin lỗi, tôi không hiểu câu hỏi của bạn.";
                    }

                    // Gửi phản hồi từ bot
                    addToChat(response, Message.SENT_BY_BOT);
                }, 5000); // 2000ms = 2 giây, có thể điều chỉnh độ trễ theo yêu cầu
            }
        });



        return view;
    }

    void addToChat(String message, String sentBy) {
        // Sử dụng requireActivity() để gọi runOnUiThread trong Fragment
        requireActivity().runOnUiThread(() -> {
            messageList.add(new Message(message, sentBy));
            messageAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
        });
    }
}