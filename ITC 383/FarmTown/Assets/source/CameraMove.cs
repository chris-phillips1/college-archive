using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraMove : MonoBehaviour
{
    public Camera cam;
    private float LowXBound;
    private float HighXBound;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        Vector3 camVec = cam.transform.position;
        Vector3 playerVec = transform.position;
        Vector3 LeftBoundary = cam.ScreenToWorldPoint(new Vector3(Screen.width * .25f, cam.transform.position.y, playerVec.z));
        Vector3 RightBoundary = cam.ScreenToWorldPoint(new Vector3(Screen.width * .75f, cam.transform.position.y, playerVec.z));
        Vector3 DownBoundary = cam.ScreenToWorldPoint(new Vector3(playerVec.x, Screen.height * .35f, transform.position.z));
        Vector3 UpBoundary = cam.ScreenToWorldPoint(new Vector3(playerVec.x, Screen.height * .65f, transform.position.z));

        if (playerVec.x > RightBoundary.x)
        {
            //Move camera the difference
            camVec.x += playerVec.x - RightBoundary.x;
            cam.transform.position = camVec;
        }
        else if (playerVec.x < LeftBoundary.x)
        {
            //Move camera the difference
            camVec.x += playerVec.x - LeftBoundary.x;
            cam.transform.position = camVec;
        }
        else if (playerVec.y < DownBoundary.y)
        {
            //Move camera the difference
            camVec.y += playerVec.y - DownBoundary.y;
            cam.transform.position = camVec;
        }
        else if (playerVec.y > UpBoundary.y)
        {
            //Move camera the difference
            camVec.y += playerVec.y - UpBoundary.y;
            cam.transform.position = camVec;
        }


        //Vector3 player = transform.position;

        //if(player.x > HighXBound)
        //{
        //    Debug.Log("Higher");
        //}
        //else if(player.x < LowXBound)
        //{
        //    Debug.Log("Lower");
        //}
        
    }
}
