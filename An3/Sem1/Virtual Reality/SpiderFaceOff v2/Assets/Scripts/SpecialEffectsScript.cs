using UnityEngine;
using System.Collections;

public class SpecialEffectsScript : MonoBehaviour {
	public AudioClip sleepingSound;
	public AudioClip alertSound;
	public AudioClip walkingSound;
	public AudioClip runningSound;
	public AudioClip attackSound;
	public AudioClip dieSound;
	public AudioClip wallFallingSound;

	public AudioSource loopingSoundSource, loopingSoundSource2;


	private static SpecialEffectsScript _instance;
	public static SpecialEffectsScript Instance
	{
		get
		{
			if(_instance == null)
				_instance = (SpecialEffectsScript) (Resources.FindObjectsOfTypeAll(typeof(SpecialEffectsScript))[0]);
			//Debug.Log (_instance);
			return _instance;
		}
	}

	public static void PlaySleepingSound() {
		if (Instance.sleepingSound != null)
			playLooped(Instance.sleepingSound);
	}
	
	public static void PlayAlertSound() {
		if (Instance.alertSound != null)
			AudioSource.PlayClipAtPoint (Instance.alertSound, Vector3.zero);
	}

	public static void PlayWalkinSound() {
		if (Instance.walkingSound != null)
			playLooped(Instance.walkingSound);
	}

	public static void PlayRunningSound() {
		if (Instance.runningSound != null)
			playLooped2(Instance.runningSound);
	}

	public static void PlayAttackSound() {
		if (Instance.attackSound != null)
			AudioSource.PlayClipAtPoint (Instance.attackSound, Vector3.zero);
	}

	public static void PlayWallFallingSound() {
		if (Instance.wallFallingSound != null)
			AudioSource.PlayClipAtPoint (Instance.wallFallingSound, Vector3.zero);
	}

	public static void PlayDieSound(float delay = 0.3f) {
		if (Instance.dieSound != null)
			Instance.StartCoroutine(Instance.PlayDieSoundDelay(Instance.dieSound, delay));
	}

	IEnumerator PlayDieSoundDelay(AudioClip aClip, float delay = 0) {
		yield return new WaitForSeconds (delay);
		AudioSource.PlayClipAtPoint (aClip, Vector3.zero);
	}

	
	public static void StopAllAudio() {
		AudioSource[] allAudioSources = GameObject.FindObjectsOfType<AudioSource>() as AudioSource[];
		for(int i=0; i<allAudioSources.Length; i++)
			allAudioSources[i].Stop();
	}

	public static void playLooped(AudioClip clip) {
		Instance.loopingSoundSource.Stop ();
		Instance.loopingSoundSource.clip = clip;
		Instance.loopingSoundSource.Play ();
	}

	public static void playLooped2(AudioClip clip) {
		Instance.loopingSoundSource2.Stop ();
		Instance.loopingSoundSource2.clip = clip;
		Instance.loopingSoundSource2.Play ();
	}

}
