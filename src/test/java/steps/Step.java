package steps;

import com.google.common.collect.Comparators;
import models.PostModel;
import models.UserModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Step {
    public static boolean isAscendingOrder(List<Integer> elements) {
        return Comparators.isInOrder(elements, Comparator.naturalOrder());
    }

    public static List<Integer> getListId(List<PostModel> postModels) {
        List<Integer> idList = new ArrayList<>();

        for (PostModel postModel: postModels) {
            idList.add(postModel.getId());
        }
        return idList;
    }

    public static UserModel getUserById(List<UserModel> users, Integer id) {
        return users.stream()
                .filter(a -> a.getId().equals(id)).findFirst().orElse(null);
    }
}
