using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class LoadScenes : MonoBehaviour
{
    public string sceneToLoad;
    public bool EscAllowed = false;

    public void LoadScene()
    {
        SceneManager.LoadScene(sceneToLoad);
    }

    void Update()
    {
        if(EscAllowed && Input.GetKeyDown(KeyCode.Escape))
            LoadScene();
    }
}
