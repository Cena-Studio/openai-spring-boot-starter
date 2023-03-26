package cool.cena.openai.pojo.chatcompletion;

import java.util.List;

public class TreeMessage {
    
    private List<TreeMessage> parent;
    private List<TreeMessage> childs;
    private ChatCompletionMessage message;
    private List<Integer> path;

    public TreeMessage(){

    }

    public TreeMessage toPath(List<Integer> target){
        int currentDepth = this.path.size();
        int targetDepth = target.size();

        for(int i = 0; i < currentDepth && i < targetDepth; i ++){

            // the target is in another branch
            if (this.path.get(i).intValue() != this.path.get(i).intValue()) {

                return this.parent.get(i - 1).toPath(target);

            // the target is a child
            }else if(currentDepth < targetDepth && i + 1 == currentDepth){

                return this.childs.get(target.get(currentDepth)).toPath(target);

            // the target is a parent
            }else if(currentDepth > targetDepth && i + 1 == targetDepth){

                return this.parent.get(i);

            }
        }

        return this;
    }

    public TreeMessage toParent(int index){
        return this.parent.get(index);
    }
    
}
