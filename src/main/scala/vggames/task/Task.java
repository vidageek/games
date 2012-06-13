package vggames.task;

public interface Task {

	JudgedTask judge(String challenge);

	String getChallenge();
}
