package vggames.shared.task;

public interface Task {

	JudgedTask judge(String challenge);

	String getChallenge();
}
