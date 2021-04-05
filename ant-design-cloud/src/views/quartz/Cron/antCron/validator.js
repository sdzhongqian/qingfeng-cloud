// import CronParser from 'cron-parser'
export const WEEK_MAP_EN = {
    'sun': '0',
    'mon': '1',
    'tue': '2',
    'wed': '3',
    'thu': '4',
    'fri': '5',
    'sat': '6',
    
  }
  
export const replaceWeekName = (c) => {
    if (c) {
        c = c.toLowerCase()
        Object.keys(WEEK_MAP_EN).forEach(k => {
        c = c.replace(new RegExp(k, 'g'), WEEK_MAP_EN[k])
        })
        c = c.replace(new RegExp('7', 'g'), '0')
    }
    return c
}
