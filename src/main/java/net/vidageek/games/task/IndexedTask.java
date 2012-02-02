package net.vidageek.games.task;

public final class IndexedTask implements Task {

	private final Task delegate;
	private final int index;

	public IndexedTask(final Task delegate, final int index) {
		this.delegate = delegate;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public JudgedTask judge(final String challenge) {
		return delegate.judge(challenge);
	}

	public String getDescription() {
		return delegate.getDescription();
	}

	public String getChallenge() {
		return delegate.getChallenge();
	}

}
