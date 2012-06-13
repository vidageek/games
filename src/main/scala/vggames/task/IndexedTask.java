package vggames.task;

import vggames.task.status.Error;

public final class IndexedTask implements TaskWithDescription {

	private final TaskWithDescription delegate;
	private final int index;

	public IndexedTask(final TaskWithDescription delegate, final int index) {
		this.delegate = delegate;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public JudgedTask judge(final String challenge) {
		try {
			return delegate.judge(challenge);
		} catch (Exception e) {
			return new Error(e);
		}
	}

	public String getDescription() {
		return delegate.getDescription();
	}

	public String getChallenge() {
		return delegate.getChallenge();
	}

	@Override
	public String toString() {
		return delegate.toString();
	}

	public String getGroupName() {
		return delegate.getGroupName();
	}
}
